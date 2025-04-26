/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastruproject;

public class PhotoManager {
    LinkedList<Photo> photosList;

    public PhotoManager() {
        photosList = new LinkedList<Photo>();
    }

    public void addPhoto(Photo p) {
        if (containsPhoto(photosList, p)) return;
        photosList.insert(p);
    }

    public void deletePhoto(String path) {
        if (photosList.empty()) return;

        photosList.findFirst();
        while (!photosList.last()) {
            if (photosList.retrieve().getPath().equals(path)) {
                photosList.remove();
                return;
            }
            photosList.findNext();
        }

        if (photosList.retrieve().getPath().equals(path)) {
            photosList.remove();
        }
    }

    public LinkedList<Photo> getPhotos() {
        return photosList;
    }

    public boolean containsPhoto(LinkedList<Photo> list, Photo p) {
        if (list.empty()) return false;

        list.findFirst();
        while (!list.last()) {
            if (list.retrieve().getPath().equals(p.getPath()))
                return true;
            list.findNext();
        }

        return list.retrieve().getPath().equals(p.getPath());
    }
}






