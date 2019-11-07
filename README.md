# sort-words-in-line-by-groups
Есть строка, состоящая из слов. Все слова в ней разделены одним пробелом. Нужно преобразовать строку в такую структуру данных, которая группирует слова по первой букве в слове. Затем вывести только группы, содержащие более одного элемента.

Группы должны быть отсортированы в алфавитном порядке. Слова внутри группы нужно сортировать по убыванию количества символов; если количество символов равное, то сортировать в алфавитном порядке.

Пример строки: String s = «Подобная архитектура обеспечивает кроссплатформенность и аппаратную переносимость программ на благодаря чему подобные программы без перекомпиляции могут выполняться на различных платформах»

Результат: 
а: [архитектура, аппаратную]
б: [благодаря, без]
н: [на, на]
п: [перекомпиляции, переносимость, платформах, программы, подобная, подобные, программ]



Если в консоли побилась кодировка IntelliJ IDEA: Settings → File Encoding → Project Encoding → IDE Encoding → UTF-8