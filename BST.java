package datastruproject;

class BSTNode<T> {
    public String keyStr;
    public T data;
    public BSTNode<T> left, right;

    public BSTNode(String keyStr, T data) {
        this.keyStr = keyStr;
        this.data = data;
        this.left = this.right = null;
    }
}

public class BST<T> {
    private BSTNode<T> root;
    private BSTNode<T> current;

    public int num_comp = 0;

    public BST() {
        root = current = null;
    }

    public void clear() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public boolean full() {
        return false;
    }

    public T retrieve() {
        if (current != null)
            return current.data;
        return null;
    }

    public boolean findKey(String keyStr) {
        num_comp = 0;
        BSTNode<T> p = root;

        while (p != null) {
            num_comp++;
            current = p;

            if (keyStr.compareTo(p.keyStr) == 0)
                return true;
            else if (keyStr.compareTo(p.keyStr) < 0)
                p = p.left;
            else
                p = p.right;
        }

        return false;
    }

    // ✅ إضافية: ترجع العنصر المرتبط بمفتاح معين (تُستخدم في InvIndexPhotoManager)
    public T findKeyReturnVal(String keyStr) {
        BSTNode<T> p = root;

        while (p != null) {
            if (keyStr.equals(p.keyStr))
                return p.data;
            else if (keyStr.compareTo(p.keyStr) < 0)
                p = p.left;
            else
                p = p.right;
        }

        return null;
    }

    public boolean insert(String keyStr, T val) {
        if (root == null) {
            root = current = new BSTNode<>(keyStr, val);
            return true;
        }

        BSTNode<T> p = current;

        if (findKey(keyStr)) {
            current = p;
            return false;
        }

        BSTNode<T> newNode = new BSTNode<>(keyStr, val);

        if (keyStr.compareTo(current.keyStr) < 0)
            current.left = newNode;
        else
            current.right = newNode;

        current = newNode;
        return true;
    }

    public boolean removeKey(String keyStr) {
        BSTNode<T> p = root;
        BSTNode<T> parent = null;

        while (p != null && !keyStr.equals(p.keyStr)) {
            parent = p;
            if (keyStr.compareTo(p.keyStr) < 0)
                p = p.left;
            else
                p = p.right;
        }

        if (p == null) return false;

        if (p.left != null && p.right != null) {
            BSTNode<T> min = p.right;
            BSTNode<T> minParent = p;

            while (min.left != null) {
                minParent = min;
                min = min.left;
            }

            p.keyStr = min.keyStr;
            p.data = min.data;

            p = min;
            parent = minParent;
            keyStr = min.keyStr;
        }

        BSTNode<T> child = (p.left != null) ? p.left : p.right;

        if (parent == null)
            root = child;
        else {
            if (keyStr.compareTo(parent.keyStr) < 0)
                parent.left = child;
            else
                parent.right = child;
        }

        return true;
    }

    public int getNumComp() {
        return num_comp;
    }

    BSTNode<LinkedList<Photo>> getRoot() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
