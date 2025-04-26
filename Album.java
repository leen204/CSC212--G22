package datastruproject;

import java.util.Scanner;

public class Album {
    private String name;
    private String condition;
    private PhotoManager manager;
    private InvIndexPhotoManager invManager;
    private int nbComps;

    public Album(String name, String condition, PhotoManager manager, InvIndexPhotoManager invManager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
        this.invManager = invManager;
        this.nbComps = 0;
    }

    public String getName() { return name; }
    public String getCondition() { return condition; }
    public int getNbComps() { return nbComps; }

    public LinkedList<Photo> getPhotos(int choice) {
        nbComps = 0;
        String[] conditionTags = condition.split("\\s*AND\\s*");
        
        if (condition.trim().isEmpty()) return manager.getPhotos();

        if (choice == 1) {
            // LinkedList Version
            LinkedList<Photo> all = manager.getPhotos();
            LinkedList<Photo> result = new LinkedList<>();
            all.findFirst();
            while (true) {
                Photo p = all.retrieve();
                boolean match = true;
                for (String tag : conditionTags) {
                    nbComps++;
                    if (!p.getTags().find(tag)) {
                        match = false;
                        break;
                    }
                }
                if (match) result.insert(p);
                if (all.last()) break;
                all.findNext();
            }
            return result;

        } else {
            // BST Version
            LinkedList<LinkedList<Photo>> lists = new LinkedList<>();
            for (String tag : conditionTags) {
                LinkedList<Photo> list = invManager.getPhotoList(tag);
                nbComps++;
                if (list == null) return new LinkedList<>();
                lists.insert(list);
            }

            // تقاطع جميع القوائم
            LinkedList<Photo> result = lists.retrieve();
            lists.findFirst();
            while (!lists.last()) {
                lists.findNext();
                result = intersect(result, lists.retrieve());
            }
            return result;
        }
    }

    private LinkedList<Photo> intersect(LinkedList<Photo> a, LinkedList<Photo> b) {
        LinkedList<Photo> result = new LinkedList<>();
        a.findFirst();
        while (true) {
            Photo pa = a.retrieve();
            b.findFirst();
            while (true) {
                if (pa.getPath().equals(b.retrieve().getPath())) {
                    result.insert(pa);
                    break;
                }
                if (b.last()) break;
                b.findNext();
            }
            if (a.last()) break;
            a.findNext();
        }
        return result;
    }
}
