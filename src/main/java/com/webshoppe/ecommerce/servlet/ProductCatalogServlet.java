package com.webshoppe.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webshoppe.ecommerce.entity.Book;
import com.webshoppe.ecommerce.entity.Flower;
import com.webshoppe.ecommerce.entity.Toy;
import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;
import com.webshoppe.ecommerce.repository.BookRepository;
import com.webshoppe.ecommerce.repository.FlowerRepository;
import com.webshoppe.ecommerce.repository.ToyRepository;
import com.webshoppe.ecommerce.service.BookCatalogService;
import com.webshoppe.ecommerce.service.FlowerCatalogService;
import com.webshoppe.ecommerce.service.ToyCatalogService;

@WebServlet("/catalog")
public class ProductCatalogServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String TOY_CATALOG_ACTION = "show-toy-catalog";
    private static final String BOOK_CATALOG_ACTION = "show-book-catalog";
    private static final String FLOWER_CATALOG_ACTION = "show-flower-catalog";

    private ToyCatalogService toyCatalogService;
    private FlowerCatalogService flowerCatalogService;
    private BookCatalogService bookCatalogService;

    @Override
    public void init() throws ServletException {
        final JdbcConnectionManager jdbcConnectionManager = new JdbcConnectionManager();
        final ToyRepository toyRepository = new ToyRepository(jdbcConnectionManager);
        final FlowerRepository flowerRepository = new FlowerRepository(jdbcConnectionManager);
        final BookRepository bookRepository = new BookRepository(jdbcConnectionManager);

        toyCatalogService = new ToyCatalogService(toyRepository);
        bookCatalogService = new BookCatalogService(bookRepository);
        flowerCatalogService = new FlowerCatalogService(flowerRepository);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        final String action = request.getParameter("action");

        String catalogContent = "No such catalog.";
        if (TOY_CATALOG_ACTION.equals(action)) {
            catalogContent = getToyCatalogContent();
        } else if (BOOK_CATALOG_ACTION.equals(action)) {
            catalogContent = getBookCatalogContent();
        } else if (FLOWER_CATALOG_ACTION.equals(action)) {
            catalogContent = getFlowerCatalogContent();
        }

        PrintWriter out = response.getWriter();
        out.println(catalogContent);
        out.flush();
        out.close();
    }

    private String getToyCatalogContent() {
        final List<Toy> toys = toyCatalogService.getToyCatalog();
        final StringBuilder stringBuilder = new StringBuilder();
        if (toys.isEmpty()) {
            stringBuilder.append("<b>Toy Catalog Empty</b>");
        } else {
            stringBuilder.append("<table class='table'>");
            stringBuilder.append("<thead>");
            stringBuilder.append("<th scope='col'>ID</th>");
            stringBuilder.append("<th scope='col'>Name</th>");
            stringBuilder.append("<th scope='col'>Description</th>");
            stringBuilder.append("<th scope='col'>Price</th>");
            stringBuilder.append("</thead>");
            toys.forEach(e -> {
                stringBuilder.append("<tr scope='row'>");
                stringBuilder.append("<td>").append(e.getId()).append("</td>");
                stringBuilder.append("<td>").append(e.getName()).append("</td>");
                stringBuilder.append("<td>").append(e.getDescription()).append("</td>");
                stringBuilder.append("<td>").append(e.getPrice()).append("</td>");
                stringBuilder.append("</tr>");
            });
            stringBuilder.append("</table>");
        }

        return stringBuilder.toString();
    }

    private String getBookCatalogContent() {
        final List<Book> books = bookCatalogService.getBookCatalog();
        final StringBuilder stringBuilder = new StringBuilder();
        if (books.isEmpty()) {
            stringBuilder.append("<b>Book Catalog Empty</b>");
        } else {
            stringBuilder.append("<table class='table'>");
            stringBuilder.append("<thead>");
            stringBuilder.append("<th scope='col'>ID</th>");
            stringBuilder.append("<th scope='col'>Name</th>");
            stringBuilder.append("<th scope='col'>Description</th>");
            stringBuilder.append("<th scope='col'>Price</th>");
            stringBuilder.append("<th scope='col'>Author</th>");
            stringBuilder.append("</thead>");
            books.forEach(e -> {
                stringBuilder.append("<tr scope='row'>");
                stringBuilder.append("<td>").append(e.getId()).append("</td>");
                stringBuilder.append("<td>").append(e.getName()).append("</td>");
                stringBuilder.append("<td>").append(e.getDescription()).append("</td>");
                stringBuilder.append("<td>").append(e.getPrice()).append("</td>");
                stringBuilder.append("<td>").append(e.getAuthor()).append("</td>");
                stringBuilder.append("</tr>");
            });
            stringBuilder.append("</table>");
        }

        return stringBuilder.toString();
    }

    private String getFlowerCatalogContent() {
        final List<Flower> flowers = flowerCatalogService.getFlowerCatalog();
        final StringBuilder stringBuilder = new StringBuilder();
        if (flowers.isEmpty()) {
            stringBuilder.append("<b>Flower Catalog Empty</b>");
        } else {
            stringBuilder.append("<table class='table'>");
            stringBuilder.append("<thead>");
            stringBuilder.append("<th scope='col'>ID</th>");
            stringBuilder.append("<th scope='col'>Name</th>");
            stringBuilder.append("<th scope='col'>Description</th>");
            stringBuilder.append("<th scope='col'>Price</th>");
            stringBuilder.append("</thead>");
            flowers.forEach(e -> {
                stringBuilder.append("<tr scope='row'>");
                stringBuilder.append("<td>").append(e.getId()).append("</td>");
                stringBuilder.append("<td>").append(e.getName()).append("</td>");
                stringBuilder.append("<td>").append(e.getDescription()).append("</td>");
                stringBuilder.append("<td>").append(e.getPrice()).append("</td>");
                stringBuilder.append("</tr>");
            });
            stringBuilder.append("</table>");
        }

        return stringBuilder.toString();
    }
}
