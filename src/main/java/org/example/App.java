package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class App {
    static EntityManagerFactory emf;
    static EntityManager em;
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        try {
            emf = Persistence.createEntityManagerFactory("ForApartments");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: Add new Apartment");
                    System.out.println("2: Delete Apartment");
                    System.out.println("3: View all Apartments");
                    System.out.println("4: Selection by price");
                    System.out.println("5: Selection by area");
                    System.out.println("6: Selection by rooms");
                    System.out.print("-> ");

                    String s = sc.nextLine();

                    switch (s) {
                        case "1":
                            addApartment(sc);
                            break;
                        case "2":
                            deleteApartment(sc);
                            break;
                        case "3":
                            viewAll();
                            break;
                        case "4":
                            selectionPrice(sc);
                            break;
                        case "5":
                            selectArea(sc);
                            break;
                        case "6":
                            selectRoom(sc);
                            break;
                        default: return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }
    private static void addApartment (Scanner sc) {
        System.out.print("Enter district: ");
        String district = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        System.out.print("Enter area (format 34.50): ");
        double area = Double.parseDouble(sc.nextLine());
        System.out.print("Enter count of rooms: ");
        int rooms = Integer.parseInt(sc.nextLine());
        System.out.print("Enter price: ");
        int price = Integer.parseInt(sc.nextLine());

        em.getTransaction().begin();
        try {
            Apartment a = new Apartment(district,address,area,rooms,price);
            em.persist(a);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void deleteApartment (Scanner sc) {
        System.out.print("Enter Apartment id for delete: ");
        long aId = Long.parseLong(sc.nextLine());
        Apartment a = em.getReference(Apartment.class, aId);

        if (a == null) {
            System.out.println("Apartment not found");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(a);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    private static void viewAll() {
        Query query = em.createQuery("select x from Apartment x", Apartment.class);
        List<Apartment> list = (List<Apartment>) query.getResultList();
        for (Apartment a: list) {
            System.out.println(a.toString());
        }
    }

    private static void selectionPrice (Scanner sc) {
        System.out.print("Enter the maximum amount you can pay: ");
        int price = Integer.parseInt(sc.nextLine());
        Query query = em.createQuery("select x from Apartment x where x.price <= :price", Apartment.class);
        query.setParameter("price", price);
        List<Apartment> list = (List<Apartment>) query.getResultList();
        if (list.size() < 1) {
            System.out.println("No results for your search");
            return;
        }
        for (Apartment a: list) {
            System.out.println(a.toString());
        }
    }

    private static void selectArea (Scanner sc) {
        System.out.print("What is the minimum area you need: ");
        double area = Double.parseDouble(sc.nextLine());
        Query query = em.createQuery("select x from Apartment x where x.area >= :area", Apartment.class);
        query.setParameter("area", area);
        List<Apartment> list = (List<Apartment>) query.getResultList();
        if (list.size() < 1) {
            System.out.println("No results for your search");
            return;
        }
        for (Apartment a: list) {
            System.out.println(a.toString());
        }
    }

    private static void selectRoom (Scanner sc) {
        System.out.print("How many rooms do you need: ");
        int rooms = Integer.parseInt(sc.nextLine());
        Query query = em.createQuery("select x from Apartment x where x.rooms = :rooms", Apartment.class);
        query.setParameter("rooms", rooms);
        List<Apartment> list = (List<Apartment>) query.getResultList();
        if (list.size() < 1) {
            System.out.println("No results for your search");
            return;
        }
        for (Apartment a: list) {
            System.out.println(a.toString());
        }
    }
}
