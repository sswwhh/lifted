# Lifted

## Запуск
Необходимо передать в качестве аргументов: 
(1) количество этажей, 
(2) высоту одного этажа, 
(3) скорость лифта, 
(4) время между открытием и закрытием дверей лифта

## Управляющие вызовы
Когда программа работает и лифт успешно создан, его можно вызывать на разных этажах.

### Формат вызова лифта с этажа:
[Направление][Этаж вызова]
Например: 
u5 -- "вверх с пятого этажа", 
d3 -- "вниз с третьего этажа".

### Формат нажатия кнопки изнутри лифта:
[l][Этаж назначения]
l20 -- "поехать на двадцатый этаж"

