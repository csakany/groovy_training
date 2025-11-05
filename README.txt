1. Bemutató Task - Adat típusok, változók, művelet (matematikai, összehasonlítás, logikai műveletek)
a,
Bemeneti paraméterek (setProperty) -> vesszővel elválasztott userId lista
filter stringet előkszíteni oData lekérdezéshez
Minta:
$filter=userId in '101001’,'101005’,'101010’,'101015’,'101017’,'101018’,'101024’,'101026’,'101032’,'101035’,'101037’,'101050'

b,
Dátum kezelés
property dateTime -> létre kell hozni egy filter stringet ami adott date timehoz tartozó hónap legelső napját hozza vissza
futtatás pillanatához tartozó hónap első nap vagy következő hónap első nap
Filter string létrehozás amit EffectiveDate-d lekérdezéshez tudunk használni
$filter=userId in '101001’,'101005’,'101010’,'101015’,'101017’,'101018’,'101024’,'101026’,'101032’,'101035’,'101037’,'101050'&fromDate=1900-01-01&toDate=2025-11-05

c,
Bemeneti paraméter 'EFFECTIVE' akkor a másodikat állítja be ha 'ACTUAL' akkor az elsőt használja, egyéb esetben állítsa üresre a filter stringet (setProperty)

1. ToDo Task
ha bemeneti paraméter 'custom' akkor setProperty értékét állítsa be custom paraméter alapján
+Mentse el a futás napját egy property-be

2. Bemutató Task - Loop
for loop - array loop embereket listázunk ahol DateOfBirth nagyobb mint x életkor
a,életkor nagyobb mint X
b, kisebb mint Y

2. ToDo Task
a, for loop - array loop embereket csinálj egy comma separated listát az emberekből és állítsd be egy property-be
b, csak azokat ahol <country_of_birth>GBR</country_of_birth>

3. Bemutató Task - While loop
Listázzuk ki az első 3 CE usert

3. ToDo Task
Listázza ki az első 3 férfit

4. Bemutató Task - Exception handling






