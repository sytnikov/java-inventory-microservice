## Inventory Management System Micro-service

This assignment involves building a Spring Boot application for a inventory management.

**Entity Mapping:**
- Implement JPA entities for each element in the ERD (Supplier, Stock, Order, OrderItem).
- Define appropriate relationships between entities using annotations (e.g., OneToMany, ManyToOne).

**Inventory Management API:**
- Implement CRUD operations for all entities.
- Stock management:
    - Create new stock entries linked to a specific supplier and product identifier.
    - Update stock information (quantity, supplier, product identifier).
    - Retrieve a list of all stock entries, or a specific stock entry by ID.
    - Filter stock entries by supplier or product identifier.
    - Consider functionalities for low stock alerts based on predefined thresholds.
- Order management:
    - Create new orders with a list of ordered products and quantities.
    - Implement logic to check stock availability before order confirmation.
    - Retrieve a list of all orders, or a specific order by ID.
    - Update order status (e.g., processing, shipped, delivered).
    - Manage order cancellations.

**Security:**
- Implement security measures with Spring Security to restrict unauthorised access to API endpoints. Consider using JWT or API Key based authentication for improved security.

**Production-Ready Considerations:**
- Implement error handling and validation for API requests.
- Consider logging and monitoring functionalities for production environments.

**Additional Functionalities:**
- **Low Stock Alerts:**
    - Implement logic to track stock levels and trigger notifications (e.g., email, SMS) when stock falls below a predefined threshold.
- **Order Status Update:**
    - When an order status is updated to "Shipped" within your system, trigger a notification process.
- **Inventory Reports:**
    - Develop functionalities to generate reports on inventory levels, sales trends, low-performing products, order fulfillment times, and purchase history.
