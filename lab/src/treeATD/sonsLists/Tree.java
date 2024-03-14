package treeATD.sonsLists; // Пакет, к которому относится класс

import exceptions.InvalidException; // Импорт класса исключения
import utils.Label; // Импорт класса Label

public class Tree { // Объявление класса дерева
    private static class ArrayNode { // Внутренний статический класс для элементов массива
        private int name; // Идентификатор узла
        private Node firstChild; // Ссылка на первого потомка узла
        private Label label; // Метка узла

        ArrayNode(int name) { // Конструктор ArrayNode
            this.name = name; // Инициализация идентификатора
            this.label = new Label(' '); // Инициализация метки узла
        }
    }

    private static class Node { // Внутренний статический класс для узлов
        private final int name; // Идентификатор узла
        private final Node next; // Ссылка на следующего брата

        Node(int name, Node next) { // Конструктор Node
            this.name = name; // Инициализация идентификатора
            this.next = next; // Инициализация следующего узла
        }
    }

    private static final int EMPTY = -1; // Константа для обозначения отсутствия элемента
    private static final int SIZE = 10; // Размер массива узлов
    private static final ArrayNode[] array = new ArrayNode[SIZE]; // Массив узлов
    private int root = EMPTY; // Индекс корня дерева
    private static int freeIndex; // Индекс первого свободного элемента в массиве

    static { // Статический инициализатор
        for (int i = 0; i < SIZE - 1; i++) { // Инициализация массива узлов
            array[i] = new ArrayNode(i + 1); // Установка связи между свободными элементами
        }
        array[SIZE - 1] = new ArrayNode(EMPTY); // Последний элемент указывает на EMPTY
        freeIndex = 0; // Начало списка свободных элементов
    }

    public int parent(int n) { // Метод поиска родителя узла
        if (n == root || n == EMPTY || n >= SIZE) return EMPTY; // Проверка условий для корня и некорректных значений
        return findParent(n, root); // Возвращение родителя узла
    }

    public int leftMostChild(int n) { // Метод поиска самого левого потомка
        if (n >= SIZE || array[n].firstChild == null) return EMPTY; // Проверка условий
        return array[n].firstChild.name; // Возвращение самого левого потомка
    }

    public int rightSibling(int n) { // Метод поиска правого сиблинга (брата)
        if (n >= SIZE || n == root) return EMPTY; // Проверка условий
        int parent = findParent(n, root); // Поиск родителя
        if (parent == EMPTY) return EMPTY; // Если родитель не найден
        Node sibling = array[parent].firstChild; // Начало поиска сиблинга
        while (sibling != null && sibling.name != n) { // Поиск узла в списке детей
            sibling = sibling.next; // Переход к следующему сиблингу
        }
        return (sibling != null && sibling.next != null) ? sibling.next.name : EMPTY; // Возврат правого сиблинга, если он существует
    }

    public Label label(int n) { // Метод получения метки узла
        if (n < SIZE && (n == root || findParent(n, root) != EMPTY)) { // Проверка условий
            return array[n].label; // Возвращение метки узла
        } else {
            throw new InvalidException("No such element"); // Исключение, если элемент не найден
        }
    }

    public Tree create(Label label) { // Метод создания узла с меткой
        if (freeIndex == EMPTY) throw new InvalidException("Full"); // Проверка на наличие свободного места
        array[freeIndex].label = label; // Установка метки новому узлу
        array[freeIndex].firstChild = (root != EMPTY) ? new Node(root, null) : null; // Установка связи с текущим корнем
        root = freeIndex; // Обновление корня
        freeIndex = array[freeIndex].name; // Обновление свободного индекса
        return this; // Возврат текущего объекта дерева
    }

    public Tree create(Label label, Tree otherTree) { // Метод создания узла с меткой и поддеревом
        if (freeIndex == EMPTY) throw new InvalidException("Full"); // Проверка на наличие свободного места
        if (otherTree == this || root == EMPTY) { // Проверка условий
            return create(label); // Создание узла только с меткой, если другое дерево это текущее дерево или корень пуст
        }
        int otherRoot = otherTree.root(); // Получение корня другого дерева
        if (otherRoot == EMPTY) return create(label); // Если другое дерево пусто, создаем узел только с меткой
        array[freeIndex].label = label; // Установка метки новому узлу
        array[freeIndex].firstChild = new Node(root, new Node(otherRoot, null)); // Установка связи с текущим и другим корнем
        root = freeIndex; // Обновление корня
        freeIndex = array[freeIndex].name; // Обновление свободного индекса
        return this; // Возврат текущего объекта дерева
    }

    public int root() { // Метод получения корня дерева
        return root; // Возвращение корня
    }

    public void makeNull() { // Метод очистки дерева
        if (root == EMPTY) return; // Если дерево уже пусто, выходим
        clearTree(root); // Очистка дерева
        array[root].name = freeIndex; // Восстановление индекса свободного элемента
        freeIndex = root; // Обновление свободного индекса
        root = EMPTY; // Установка корня в пустое значение
    }

    private int findParent(int child, int current) { // Вспомогательный метод поиска родителя
        Node childNode = array[current].firstChild; // Начало поиска с первого потомка текущего узла
        while (childNode != null) { // Пока есть потомки
            if (childNode.name == child) return current; // Если найден потомок, возвращаем текущий узел как родителя
            int parent = findParent(child, childNode.name); // Рекурсивный поиск в потомках
            if (parent != EMPTY) return parent; // Если найден родитель, возвращаем его
            childNode = childNode.next; // Переход к следующему потомку
        }
        return EMPTY; // Возвращаем EMPTY, если родитель не найден
    }

    private void clearTree(int nodeIndex) { // Вспомогательный метод очистки дерева
        Node child = array[nodeIndex].firstChild; // Начало с первого потомка узла
        while (child != null) { // Пока есть потомки
            if (array[child.name].firstChild != null) { // Если у потомка есть свои потомки
                clearTree(child.name); // Рекурсивная очистка потомков
            }
            array[child.name].name = freeIndex; // Возвращение узла в список свободных
            freeIndex = child.name; // Обновление свободного индекса
            child = child.next; // Переход к следующему потомку
        }
    }

    public static void print() { // Метод печати дерева
        System.out.println("Label"); // Печать заголовка
        for (int i = 0; i < SIZE; i++) { // Перебор всех узлов
            System.out.print("[" + array[i].name + "] "); // Печать индекса узла
            if (array[i].label.getValue() == (' ')) { // Если метка узла пуста
                System.out.print("* "); // Печать звездочки
            } else {
                System.out.print(array[i].label.getValue() + " "); // Печать метки узла
            }
            for (Node child = array[i].firstChild; child != null; child = child.next) { // Перебор всех потомков
                System.out.print("-> [" + (child.name+1) + "] "); // Печать связей с потомками
            }
            System.out.println(); // Переход на новую строку
        }
    }

    public void inorderTraversal(int index) { // Метод обхода дерева
        if (index < 0 || index >= SIZE || array[index].firstChild == null) { // Проверка условий
            System.out.print(array[index].label.getValue() + " "); // Печать метки узла
            return; // Возврат
        }
        inorderTraversal(array[index].firstChild.name); // Рекурсивный обход левых потомков
        System.out.print(array[index].label.getValue() + " "); // Печать метки текущего узла
        for (Node child = array[index].firstChild.next; child != null; child = child.next) { // Рекурсивный обход правых потомков
            inorderTraversal(child.name); // Обход потомков
        }
    }
}
