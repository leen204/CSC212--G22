package datastruproject;

public class InvIndexPhotoManager {

    private LinkedList<Photo> photosList;
    private BST<LinkedList<Photo>> index;

    public InvIndexPhotoManager() {
        photosList = new LinkedList<>();
        index = new BST<>();
    }

    public void addPhoto(Photo p) {
        // أولًا: نتحقق إذا كانت موجودة مسبقًا في القائمة
        if (containsPhoto(photosList, p)) return;

        // نضيفها إلى القائمة العامة
        photosList.insert(p);

        // نضيفها إلى الفهرس العكسي لكل تاج
        LinkedList<String> tags = p.getTags();
        tags.findFirst();
        while (true) {
            String tag = tags.retrieve();

            // ابحث عن القائمة المرتبطة بالتاج
            LinkedList<Photo> list = index.findKeyReturnVal(tag);

            if (list == null) {
                list = new LinkedList<>();
                list.insert(p);
                index.insert(tag, list);
            } else {
                if (!containsPhoto(list, p)) {
                    list.insert(p);
                }
            }

            if (tags.last()) break;
            tags.findNext();
        }
    }

    public void deletePhoto(String path) {
        if (photosList.empty()) return;

        photosList.findFirst();
        while (!photosList.last()) {
            if (photosList.retrieve().getPath().equals(path)) break;
            photosList.findNext();
        }

        if (!photosList.retrieve().getPath().equals(path)) return;

        Photo target = photosList.retrieve();
        photosList.remove();

        LinkedList<String> tags = target.getTags();
        tags.findFirst();
        while (true) {
            String tag = tags.retrieve();
            LinkedList<Photo> list = index.findKeyReturnVal(tag);

            if (list != null && !list.empty()) {
                list.findFirst();
                while (!list.last()) {
                    if (list.retrieve().getPath().equals(path)) {
                        list.remove();
                        break;
                    }
                    list.findNext();
                }

                if (list.retrieve().getPath().equals(path)) {
                    list.remove();
                }

                // إذا أصبحت القائمة فاضية نحذف التاج من BST
                if (list.empty()) {
                    index.removeKey(tag);
                }
            }

            if (tags.last()) break;
            tags.findNext();
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

    // الدالة المهمة اللي نادت عليها ألبوم
    public LinkedList<Photo> getPhotoList(String tag) {
        LinkedList<Photo> list = index.findKeyReturnVal(tag);
        return list;
    }
}
