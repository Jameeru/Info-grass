package com.example;

import com.example.entity.*;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // Q1. Write a program to list all products with their categories using:
            listProductsWithCategoriesPureOrm(session);
            listProductsWithCategoriesHql(session);
            listProductsWithCategoriesNative(session);

            // Q2. Write a program to calculate the average order value by category
            
            averageOrderValueByCategoryPureOrm(session);
            averageOrderValueByCategoryHql(session);
            averageOrderValueByCategoryNative(session);

            // Q3. Write a program to calculate user spending in the last 6 months.
            //     For each user, display:
            //       - Username
            //       - Total amount spent
            //       - Date of last order
            //       - Count of distinct products purchased
            userSpendingLastSixMonthsPureOrm(session);
            userSpendingLastSixMonthsHql(session);
            userSpendingLastSixMonthsNative(session);

            tx.commit();
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
            HibernateUtil.shutdown();
        }
    }

    // ================== Q1: Products with Categories ==================

    //  Pure ORM (Criteria API)
    private static void listProductsWithCategoriesPureOrm(Session session) {
        System.out.println("\n=== Products with Categories (Pure ORM) ===");

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        cq.select(root);

        List<Product> products = session.createQuery(cq).getResultList();

        for (Product p : products) {
            String category = (p.getCategory() != null) ? p.getCategory().getName() : "No Category";
            System.out.println("[ORM] Product: " + p.getName() + ", Category: " + category);
        }
    }

    //  HQL
    private static void listProductsWithCategoriesHql(Session session) {
        System.out.println("\n=== Products with Categories (HQL) ===");

        String hql = "SELECT p.name, c.name FROM Product p LEFT JOIN p.category c";
        List<Object[]> results = session.createQuery(hql, Object[].class).list();

        for (Object[] row : results) {
            String category = row[1] != null ? row[1].toString() : "No Category";
            System.out.println("[HQL] Product: " + row[0] + ", Category: " + category);
        }
    }

    //   Native SQL
    private static void listProductsWithCategoriesNative(Session session) {
        System.out.println("\n=== Products with Categories (Native SQL) ===");

        String sql = "SELECT p.name, c.name FROM products p LEFT JOIN categories c ON p.category_id = c.category_id";
        List<Object[]> results = session.createNativeQuery(sql, Object[].class).list();

        for (Object[] row : results) {
            String category = row[1] != null ? row[1].toString() : "No Category";
            System.out.println("[SQL] Product: " + row[0] + ", Category: " + category);
        }
    }

    // ================== Q2: Average Order Value by Category ==================

    //   Pure ORM (Criteria API)
    private static void averageOrderValueByCategoryPureOrm(Session session) {
        System.out.println("\n=== Average Order Value by Category (Pure ORM) ===");

        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<OrderItem> oi = cq.from(OrderItem.class);
        Join<OrderItem, com.example.entity.Order> o = oi.join("order");
        Join<OrderItem, Product> p = oi.join("product");
        Join<Product, Category> c = p.join("category");

        cq.multiselect(c.get("name"), cb.avg(o.get("totalAmount")))
                .where(cb.greaterThanOrEqualTo(o.get("placedAt"), sixMonthsAgo))
                .groupBy(c.get("name"))
                .having(cb.gt(cb.count(o.get("id")), 0))
                .orderBy(cb.desc(cb.avg(o.get("totalAmount"))));

        List<Object[]> results = session.createQuery(cq).getResultList();

        for (Object[] row : results) {
            BigDecimal avgValue = new BigDecimal(row[1].toString())
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println("[ORM] Category: " + row[0] + ", Average Order Value: " + avgValue);
        }
    }

    //   HQL
    private static void averageOrderValueByCategoryHql(Session session) {
        System.out.println("\n=== Average Order Value by Category (HQL) ===");

        String hql = "SELECT c.name, AVG(o.totalAmount) " +
                "FROM OrderItem oi JOIN oi.order o JOIN oi.product p JOIN p.category c " +
                "WHERE o.placedAt >= :sixMonthsAgo " +
                "GROUP BY c.name " +
                "HAVING COUNT(o.orderId) > 0 " +
                "ORDER BY AVG(o.totalAmount) DESC";
        List<Object[]> results = session.createQuery(hql, Object[].class)
                .setParameter("sixMonthsAgo", LocalDateTime.now().minusMonths(6))
                .list();

        for (Object[] row : results) {
            BigDecimal avgValue = new BigDecimal(row[1].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println("[HQL] Category: " + row[0] + ", Average Order Value: " + avgValue);
        }
    }

    //   Native SQL
    private static void averageOrderValueByCategoryNative(Session session) {
        System.out.println("\n=== Average Order Value by Category (Native SQL) ===");

        String sql = "SELECT c.name, AVG(o.total_amount) AS avg_order_value " +
                "FROM order_items oi " +
                "JOIN orders o ON oi.order_id = o.order_id " +
                "JOIN products p ON oi.product_id = p.product_id " +
                "JOIN categories c ON p.category_id = c.category_id " +
                "WHERE o.placed_at >= :sixMonthsAgo " +
                "GROUP BY c.category_id, c.name " +
                "HAVING COUNT(o.order_id) > 0 " +
                "ORDER BY avg_order_value DESC";
        List<Object[]> results = session.createNativeQuery(sql, Object[].class)
                .setParameter("sixMonthsAgo", LocalDateTime.now().minusMonths(6))
                .list();

        for (Object[] row : results) {
            BigDecimal avgValue = new BigDecimal(row[1].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println("[SQL] Category: " + row[0] + ", Average Order Value: " + avgValue);
        }
    }

    // ================== Q3: User Spending in Last 6 Months ==================

    //   Pure ORM (Criteria API)
    private static void userSpendingLastSixMonthsPureOrm(Session session) {
        System.out.println("\n=== User Spending Last 6 Months (Pure ORM) ===");

        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<User> u = cq.from(User.class);
        Join<User, com.example.entity.Order> o = u.join("orders");
        Join<com.example.entity.Order, OrderItem> oi = o.join("orderItems");

        cq.multiselect(
                        u.get("username"),
                        cb.sum(o.get("totalAmount")),
                        cb.greatest(o.<LocalDateTime>get("placedAt")),
                        cb.countDistinct(oi.get("product").get("id"))
                )
                .where(cb.greaterThanOrEqualTo(o.get("placedAt"), sixMonthsAgo))
                .groupBy(u.get("username"))
                .orderBy(cb.desc(cb.sum(o.get("totalAmount"))));

        List<Object[]> results = session.createQuery(cq).getResultList();

        for (Object[] row : results) {
            System.out.println("[ORM] User: " + row[0] + ", Total Spent: " + row[1] +
                    ", Last Order: " + row[2] + ", Distinct Products: " + row[3]);
        }
    }

    //   HQL
    private static void userSpendingLastSixMonthsHql(Session session) {
        System.out.println("\n=== User Spending Last 6 Months (HQL) ===");
        String hql = "SELECT u.username, SUM(o.totalAmount), MAX(o.placedAt), COUNT(DISTINCT oi.product.id) " +
                "FROM User u JOIN u.orders o JOIN o.orderItems oi " +
                "WHERE o.placedAt >= :sixMonthsAgo " +
                "GROUP BY u.username ORDER BY SUM(o.totalAmount) DESC";
        List<Object[]> results = session.createQuery(hql, Object[].class)
                .setParameter("sixMonthsAgo", LocalDateTime.now().minusMonths(6))
                .list();

        for (Object[] row : results) {
            System.out.println("[HQL] User: " + row[0] + ", Total Spent: " + row[1] +
                    ", Last Order: " + row[2] + ", Distinct Products: " + row[3]);
        }
    }

    //   Native SQL
    private static void userSpendingLastSixMonthsNative(Session session) {
        System.out.println("\n=== User Spending Last 6 Months (Native SQL) ===");

        String sql = "SELECT u.username, SUM(o.total_amount), MAX(o.placed_at), COUNT(DISTINCT oi.product_id) " +
                "FROM users u JOIN orders o ON u.user_id = o.user_id " +
                "JOIN order_items oi ON o.order_id = oi.order_id " +
                "WHERE o.placed_at >= :sixMonthsAgo " +
                "GROUP BY u.user_id, u.username ORDER BY SUM(o.total_amount) DESC";
        List<Object[]> results = session.createNativeQuery(sql, Object[].class)
                .setParameter("sixMonthsAgo", LocalDateTime.now().minusMonths(6))
                .list();

        for (Object[] row : results) {
            System.out.println("[SQL] User: " + row[0] + ", Total Spent: " + row[1] +
                    ", Last Order: " + row[2] + ", Distinct Products: " + row[3]);
        }
    }
}
