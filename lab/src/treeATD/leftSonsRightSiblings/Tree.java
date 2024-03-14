package treeATD.leftSonsRightSiblings;

import exceptions.InvalidException;
import utils.Label;

public class Tree {
    private static class Node{
        int name; // Номер узла
        int rightSibling; // Индекс правого сиблинга (брата)
        int leftSon; // Индекс левого сына
        Label label; // Метка узла
        public Node(int name, int rightSibling, int leftSon, char n){
            this.name = name;
            this.rightSibling = rightSibling;
            this.leftSon = leftSon;
            this.label = new Label(n); // Инициализация метки
        }
    }

    private static final Node[] array; // Массив для хранения узлов дерева
    private static int freeIndex; // Индекс следующего свободного элемента в массиве
    private static final int SIZE = 10; // Размер массива
    private final static int EMPTY = -1; // Константа для обозначения пустого значения
    private int rootIndex; // Индекс корня дерева

    static {
        array = new Node[SIZE]; // Инициализация массива
        for (int i = 0; i < SIZE; i ++){
            // Инициализация узлов как свободных, связывая их в список свободных узлов
            array[i] = new Node(i == SIZE - 1 ? EMPTY : i + 1, EMPTY, EMPTY,' ');
        }
        freeIndex = 0; // Начальный индекс свободного элемента
    }

    public Tree() { rootIndex = EMPTY; }; // Конструктор инициализирует дерево без узлов

    // Метод для нахождения родителя узла n
    public int parent(int n) {
        if (n == rootIndex || n == EMPTY || n >= SIZE) return EMPTY; // Корневой узел родителя не имеет
        return findParent(n, rootIndex); // Вызов вспомогательного метода для поиска родителя
    }
    // Метод возвращает индекс левого самого ребенка узла n
    public int leftMostChild(int n) {
        if (n < 0 || n >= SIZE || array[n].leftSon == EMPTY) return EMPTY; // Проверка на существование левого сына
        return array[n].leftSon;
    }

    // Метод возвращает индекс правого сиблинга (брата) узла n
    public int rightSibling(int n) {
        if (n < 0 || n >= SIZE || array[n].rightSibling == EMPTY) return EMPTY; // Проверка на существование правого сиблинга
        return array[n].rightSibling;
    }

    // Метод возвращает метку узла n
    public Label label(int n) {
        if (n < 0 || n >= SIZE) throw new InvalidException("Out of bounds"); // Проверка на выход индекса за пределы массива
        if (array[n].label == null) throw new InvalidException(" There is no label"); // Проверка на наличие метки
        return array[n].label;
    }

    // Метод создает новое дерево или добавляет узел с меткой label
    public Tree create(Label label) {
        if(freeIndex == EMPTY) throw new InvalidException("full"); // Проверка на наличие свободного места в массиве
        // Если дерево не пустое, инициализируем корневой узел
        if (rootIndex != EMPTY) {
            array[freeIndex].leftSon = rootIndex;
        }
        array[freeIndex].label = label;
        rootIndex = freeIndex;
        freeIndex = array[freeIndex].name; // Обновление индекса свободного элемента
        return this;
    }
    //
    // Метод создает новое дерево с меткой label и добавляет к нему другое дерево tree
    public Tree create(Label label, Tree tree) {
        if(freeIndex == EMPTY) throw new InvalidException("full"); // Проверка на наличие свободного места
        // Если базовое дерево пустое, используем метод create другого дерева
        if (rootIndex == EMPTY) return tree.create(label);
        // Если добавляемое дерево пустое, добавляем метку
        if(tree.root() == EMPTY || tree == this) return create(label);
        array[freeIndex].label = label;
        array[freeIndex].leftSon = rootIndex;
        array[rootIndex].rightSibling = tree.root();
        rootIndex = freeIndex;
        freeIndex = array[freeIndex].name; // Обновление индекса свободного элемента
        return this;
    }

    public int root() {
        return rootIndex; // Возвращает индекс корня дерева
    }

    // Метод очищает дерево
    public void makeNull() {
        clearSubtree(rootIndex); // Очистка поддеревьев
        rootIndex = EMPTY; // Сброс корня дерева
    }

    // Рекурсивный метод для очистки поддерева, начиная с узла node
    private void clearSubtree(int node) {
        if (node == EMPTY) return; // Базовый случай рекурсии
        clearSubtree(array[node].leftSon); // Очистка левого поддерева
        clearSubtree(array[node].rightSibling); // Очистка правых сиблингов
        array[node] = new Node(freeIndex, EMPTY, EMPTY,' '); // Сброс узла
        freeIndex = node; // Добавление узла в список свободных
    }

    // Вспомогательный метод для поиска родителя узла child
    private int findParent(int child, int parentCandidate) {
        if (parentCandidate == EMPTY || child == EMPTY) return EMPTY; // Базовый случай
        int currentChild = array[parentCandidate].leftSon;
        while (currentChild != EMPTY) {
            if (currentChild == child) return parentCandidate; // Найден родитель
            int potentialParent = findParent(child, currentChild); // Рекурсивный поиск среди детей
            if (potentialParent != EMPTY) return potentialParent;
            currentChild = array[currentChild].rightSibling;
        }
        return EMPTY; // Родитель не найден
    }

    // Метод для печати дерева
    public static void print() {
        System.out.printf("%2s  %5s  %s  %n","LeftSon", "Label", "RightSibling");
        for (int i = 0; i < SIZE - 1; i++) {
            System.out.printf("%7d |%3c  | %7d %n",
                    array[i].leftSon,
                    array[i].label.value,
                    array[i].rightSibling
            );
        }
        System.out.printf("%7d |%3s  | %7d %n",
                array[SIZE - 1].leftSon,
                " ", // Последний элемент массива для красивой печати
                array[SIZE - 1].rightSibling
        );
        System.out.println();
    }

    // Метод для обхода дерева в глубину и печати меток в порядке обхода
    public void inorderTraversal(int index) {
        if(index == EMPTY) return; // Если индекс пуст, завершаем выполнение
        if(array[index].leftSon == EMPTY) {
            System.out.print(array[index].label.value + " "); // Печать метки, если у узла нет левого сына
        }
        else {
            inorderTraversal(array[index].leftSon); // Рекурсивный обход левого поддерева
            System.out.print(array[index].label.value + " "); // Печать метки текущего узла
            inorderTraversal(array[array[index].leftSon].rightSibling); // Рекурсивный обход правого поддерева
        }
    }
}
