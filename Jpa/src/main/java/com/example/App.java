package com.example;

import com.example.entity.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            emf = Persistence.createEntityManagerFactory("my-persistence-unit");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            // LOW COMPLEXITY: List products with categories
            listProductsWithCategories(em);

            // MEDIUM COMPLEXITY: Average order value by category
            averageOrderValueByCategory(em);

            // HIGH COMPLEXITY: User spending in last 6 months
            userSpendingLastSixMonths(em);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    //                      LOW COMPLEXITY
    // 1. List products with categories (Pure JPA Criteria API)
    private static void listProductsWithCategories(EntityManager em) {
        System.out.println("\n=== Products with Categories (JPA Criteria) ===");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        cq.select(root);

        List<Product> products = em.createQuery(cq).getResultList();

        for (Product p : products) {
            String category = (p.getCategory() != null) ? p.getCategory().getName() : "No Category";
            System.out.println("[JPA] Product: " + p.getName() + ", Category: " + category);
        }
    }

    //  MEDIUM COMPLEXITY
    // 2. Average order value by category (Pure JPA Criteria API)
    private static void averageOrderValueByCategory(EntityManager em) {
        System.out.println("\n=== Average Order Value by Category (JPA Criteria) ===");

        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<OrderItem> oi = cq.from(OrderItem.class);
        Join<OrderItem, Order> o = oi.join("order");
        Join<OrderItem, Product> p = oi.join("product");
        Join<Product, Category> c = p.join("category");

        cq.multiselect(c.get("name"), cb.avg(o.get("totalAmount")))
                .where(cb.greaterThanOrEqualTo(o.get("placedAt"), sixMonthsAgo))
                .groupBy(c.get("name"))
                .having(cb.gt(cb.count(o.get("id")), 0))
                .orderBy(cb.desc(cb.avg(o.get("totalAmount"))));

        List<Object[]> results = em.createQuery(cq).getResultList();

        for (Object[] row : results) {
            BigDecimal avgValue = new BigDecimal(row[1].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println("[JPA] Category: " + row[0] + ", Average Order Value: " + avgValue);
        }
    }

    // ================== HIGH COMPLEXITY ==================
    // 3. User spending in last 6 months (Pure JPA Criteria API)
    private static void userSpendingLastSixMonths(EntityManager em) {
        System.out.println("\n=== User Spending Last 6 Months (JPA Criteria) ===");

        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<User> u = cq.from(User.class);
        Join<User, Order> o = u.join("orders");
        Join<Order, OrderItem> oi = o.join("orderItems");

        cq.multiselect(
                        u.get("username"),
                        cb.sum(o.get("totalAmount")),
                        cb.greatest(o.<LocalDateTime>get("placedAt")),
                        cb.countDistinct(oi.get("product").get("id"))
                )
                .where(cb.greaterThanOrEqualTo(o.get("placedAt"), sixMonthsAgo))
                .groupBy(u.get("username"))
                .orderBy(cb.desc(cb.sum(o.get("totalAmount"))));

        List<Object[]> results = em.createQuery(cq).getResultList();

        for (Object[] row : results) {
            System.out.println("[JPA] User: " + row[0] +
                    ", Total Spent: " + row[1] +
                    ", Last Order: " + row[2] +
                    ", Distinct Products: " + row[3]);
        }
    }
}
