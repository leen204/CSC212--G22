package datastruproject;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        InvIndexPhotoManager invmanager = new InvIndexPhotoManager();
        PhotoManager manager = new PhotoManager();

        Photo photo1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        Photo photo2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
        Photo photo3 = new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));

        invmanager.addPhoto(photo1);
        invmanager.addPhoto(photo2);
        invmanager.addPhoto(photo3);

        manager.addPhoto(photo1);
        manager.addPhoto(photo2);
        manager.addPhoto(photo3);

        Album album1 = new Album("Album1", "bear", manager, invmanager);
        Album album2 = new Album("Album2", "animal AND grass", manager, invmanager);
        Album album3 = new Album("Album3", "", manager, invmanager);

        System.out.println("Get photo1 path and tags:");
        System.out.println("photo1 path: " + photo1.getPath());
        printLL(photo1.getTags());

        // album1
        System.out.println("\n\nGet album1 name, condition, and photos:");
        System.out.println("album1 name: " + album1.getName());
        System.out.println("album1 condition: " + album1.getCondition());
        int choice1 = getChoice(input);
        printLLPhoto(album1.getPhotos(choice1));
        System.out.println("Number of comparisons of condition \"" + album1.getCondition() + "\" is " + album1.getNbComps());

        // album2
        System.out.println("\n\nGet album2 name, condition, and photos:");
        System.out.println("album2 name: " + album2.getName());
        System.out.println("album2 condition: " + album2.getCondition());
        int choice2 = getChoice(input);
        printLLPhoto(album2.getPhotos(choice2));
        System.out.println("Number of comparisons of condition \"" + album2.getCondition() + "\" is " + album2.getNbComps());

        // album3
        System.out.println("\n\nGet album3 name, condition, and photos:");
        System.out.println("album3 name: " + album3.getName());
        System.out.println("album3 condition: " + album3.getCondition());
        int choice3 = getChoice(input);
        printLLPhoto(album3.getPhotos(choice3));
        System.out.println("Number of comparisons of condition \"" + album3.getCondition() + "\" is " + album3.getNbComps());

        System.out.println("\n\nDelete the photo 'bear.jpg':");
        manager.deletePhoto("bear.jpg");

        // album3 again after deletion
        System.out.println("\n\nGet album3 name, condition, and photos:");
        System.out.println("album3 name: " + album3.getName());
        System.out.println("album3 condition: " + album3.getCondition());
        int choice4 = getChoice(input);
        printLLPhoto(album3.getPhotos(choice4));
        System.out.println("Number of comparisons of condition \"" + album3.getCondition() + "\" is " + album3.getNbComps());
    }

    private static int getChoice(Scanner input) {
        System.out.println("1. Linked List");
        System.out.println("2. BST");
        System.out.print("Enter your choice: ");
        return input.nextInt();
    }

    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<String>();
        String[] tagsArray = tags.split("\\s*,\\s*");
        for (String tag : tagsArray) {
            result.insert(tag);
        }
        return result;
    }

    private static void printLL(LinkedList<String> list) {
        if (list.empty()) return;
        list.findFirst();
        while (true) {
            System.out.print(list.retrieve() + " ");
            if (list.last()) break;
            list.findNext();
        }
        System.out.println();
    }

    private static void printLLPhoto(LinkedList<Photo> list) {
        if (list.empty()) return;
        list.findFirst();
        while (true) {
            System.out.println(list.retrieve().getPath());
            if (list.last()) break;
            list.findNext();
        }
    }
}
