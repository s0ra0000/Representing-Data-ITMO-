Конструктор ArrayList(capacity: int):
    Описание: Создает новый объект ArrayList с заданной начальной емкостью.
    Входные параметры:
        capacity: начальная емкость списка
    Действия:
        Создается новый массив array заданной емкости capacity.
        Переменная size устанавливается в 0.

first(): Position
    Описание: Возвращает позицию после первого элемента в списке.
    Действия:
        Если size > 0, возвращается позиция 1.
        Если size равен 0, возвращается позиция после последнего элемента, что эквивалентно size + 1.

end(): Position
    Описание: Возвращает позицию после последнего элемента в списке.
    Действия:
        Создается и возвращается позиция, равная size + 1.

printlist():
    Описание: Выводит все элементы списка на экран.
    Действия:
        Итерируется по массиву array и выводит каждый элемент на экран.

insert(x: Data, p: Position)
    Описание: Вставляет элемент x в указанную позицию p в списке.
    Входные параметры:
        x: элемент для вставки
        p: позиция, на которую нужно вставить элемент
    Действия:
        Проверяется допустимость позиции p. Если p меньше 1 или больше size + 1, вставка не выполняется.
        Если size превышает емкость массива array, выполняется увеличение емкости с помощью метода resize().
        Элементы, начиная с последнего и до позиции p, сдвигаются на одну позицию вправо.
        Элемент x вставляется на позицию p.
        Размер списка size увеличивается на 1.

locate(x: Data): Position
    Описание: Находит позицию элемента x в списке.
    Входные параметры:
        x: элемент, позицию которого нужно найти
    Возвращаемое значение: позиция элемента (тип Position)
    Действия:
        Итерируется по массиву array и сравнивает каждый элемент с x.
        Если элемент найден, возвращается позиция этого элемента (1-based).
        Если элемент не найден, возвращается позиция после последнего элемента, что эквивалентно size + 1.

retrieve(p: Position): Data
    Описание: Возвращает элемент, находящийся на позиции p в списке.
    Входные параметры:
        p: позиция элемента
    Возвращаемое значение: элемент (тип Data)
    Действия:
        Проверяется допустимость позиции p. Если p меньше 1 или больше size, выбрасывается исключение.
        Возвращается элемент списка, находящийся на позиции p.

delete(p: Position)
    Описание: Удаляет элемент на позиции p из списка.
    Входные параметры:
        p: позиция элемента для удаления
    Действия:
        Проверяется допустимость позиции p. Если p меньше 1 или больше size, удаление не выполняется.
        Элементы, начиная с позиции p, сдвигаются на одну позицию влево.
        Последний элемент списка очищается (устанавливается в null).
        Размер списка size уменьшается на 1.

next(p: Position): Position
    Описание: Возвращает позицию следующего элемента после позиции p.
    Входные параметры:
        p: текущая позиция
    Возвращаемое значение: позиция следующего элемента (тип Position)
    Действия:
        Проверяется допустимость позиции p. Если p меньше 1 или больше size, выбрасывается исключение.
        Возвращается позиция, равная p + 1.

previous(p: Position): Position
    Описание: Возвращает позицию предыдущего элемента перед позицией p.
    Входные параметры:
        p: текущая позиция
    Возвращаемое значение: позиция предыдущего элемента (тип Position)
    Действия:
        Проверяется допустимость позиции p. Если p меньше или равно 1 или больше size + 1, выбрасывается исключение.
        Возвращается позиция, равная p - 1.

makenull()
    Описание: Очищает список, удаляя все элементы.
    Действия:
        Все элементы массива array устанавливаются в null.
        Размер списка size устанавливается в 0.

Вспомогательный метод resize()
    Описание: Увеличивает размер внутреннего массива при необходимости.
    Действия:
        Создается новый массив newArray с увеличенной емкостью (обычно удваивается).
        Элементы из текущего массива array копируются в новый массив newArray.
        Массив array переприсваивается ссылкой на новый массив newArray.