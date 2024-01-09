# Лабораторная работа #1

## Дисциплина
Функциональное программирование

## Работу выполнял

Студент группы P33102 Елохов Даниил

## Цель:
Освоить базовые приёмы и абстракции функционального программирования: функции, поток управления и поток данных, сопоставление с образцом, рекурсия, свёртка, отображение, работа с функциями как с данными, списки.

В рамках лабораторной работы вам предлагается решить несколько задач [проекта Эйлер](https://projecteuler.net/archives). Список задач -- ваш вариант.

Для каждой проблемы должно быть представлено несколько решений:

1. монолитные реализации с использованием:
    - хвостовой рекурсии;
    - рекурсии (вариант с хвостовой рекурсией не является примером рекурсии);
2. модульной реализации, где явно разделена генерация последовательности, фильтрация и свёртка (должны использоваться функции reduce/fold, filter и аналогичные);
3. генерация последовательности при помощи отображения (map);
4. работа со спец. синтаксисом для циклов (где применимо);
5. работа с бесконечными списками для языков, поддерживающих ленивые коллекции или итераторы как часть языка (к примеру Haskell, Clojure);
6. реализация на любом удобном для вас традиционном языке программирования для сравнения.

Требуется использовать идиоматичный для технологии стиль программирования.

### Содержание отчёта:

- титульный лист;
- описание проблемы;
- ключевые элементы реализации с минимальными комментариями;
- выводы (отзыв об использованных приёмах программирования).

### Примечания:

- необходимо понимание разницы между ленивыми коллекциями и итераторами;
- нужно знать особенности используемой технологии и того, как работают использованные вами приёмы.


## Вариант

[Задание номер 1](https://projecteuler.net/problem=1)

[Задание номер 30](https://projecteuler.net/problem=30)

## Ход работы

### Флоу GH Actions
```yml
name: my-gha-action

on: [push]

jobs:
  boot:

    runs-on: ubuntu-latest 

    steps:
      - uses: actions/checkout@v3

      - name: Prepare java
        uses: actions/setup-java@v3
        with:
          distribution: 'zulu'
          java-version: '8'

      - name: Install clojure
        uses: DeLaGuardo/setup-clojure@12.1
        with:
          cli: 1.10.1.693
          clj-kondo: 2022.05.31
          cljfmt: 0.10.2

      - name: Run Lint
        run: clj-kondo --lint src test

      - name: Run Test
        run: clojure -Mtest

```

## Тесты
### Task1
```clj
(ns first-task-test
  (:require [clojure.test :refer [deftest testing is]]
            [first-task :as first])
  )

(def answer 233168)

(deftest test-first-task
  (testing "Recursion"
    (is (= answer (first/task1-rec 0 1000)))
    (is (= answer (first/task1-rec-tail 0 1000 0)))
    )
  (testing "Modular"
    (is (= answer (first/task1 1000)))
    )
  (testing "Lazy"
    (is (= answer (first/task1-lazy 1000)))
    )
  )
```

### Task2
```clj
(ns second-task-test
  (:require [clojure.test :refer [deftest testing is]]
            [second-task :as second])
  )

(def answer 8302)

(deftest test-second-task
  (testing "Recursion"
    (is (= answer (second/task2-rec 0 9999)))
    (is (= answer (second/task2-rec-tail 0 0 9999)))
    )
  (testing "Modular"
    (is (= answer (second/task2 9999)))
    )
  (testing "Lazy"
    (is (= answer (second/task2-lazy 9999)))
    )
  )
```

## Решения
### Task1
```clj
(ns first-task)

;; Булевая Функция проверяющая число по условиям
(defn mySeq [x] (or (zero? (mod x 5)) (zero? (mod x 3))))

;; Модульное решение
(defn task1 [maxD]
  (reduce
    (fn [acc x]
      (if (mySeq x)
        (+ acc x)
        acc
        )
      )
    (range maxD)
    )
  )

;; Решение рекурсией
(defn task1-rec [now maxD]
  (if (< now maxD)

    (let [next (task1-rec (+ now 1) maxD)]

      (if (mySeq now)
        (+ now next)
        next
        )

      )
    0
    ))

;; Решение хвостовой рекурсией
(defn task1-rec-tail [now maxD accum]
  (if (< now maxD)
    (if (mySeq now)
      (recur (+ now 1) maxD (+ accum now))
      (recur (+ now 1) maxD accum)
      )
    accum
    ))

;; Рещение в виде Ленивых списков
(defn task1-lazy
  ([] (task1-lazy 1000))
  ([x] (reduce (fn [acc n] (if (mySeq n) (+ acc n) acc)) (range x))
   )
  )
```
### Task2
```clj
(ns second-task)

;; Функция разделение числа по его числам в список
(defn splitDig [n]
  (map (comp read-string str) (str n)))

;; Функция возведения в 5ю степень
(defn mult5 [x] (* x x x x x))

;; Буллевая функция соответствия условию 
(defn sumMultedDigOfNumber [x]
  (reduce (fn
            [acc, n]
            (+ acc (mult5 n))
            )
          0
          (splitDig x)
          )
  )

;; Модульное решение
(defn task2 [maxE]
  (reduce
    +
    0
    (filter
      (fn [x] (=
                (sumMultedDigOfNumber x)
                x
                )
        )
      (range maxE)
      )
    )
  )

;; Решение рекурсией
(defn task2-rec [now maxE]
  (if (<= now maxE)
    (if (=
          (sumMultedDigOfNumber now)
          now
          )
      (+ now (task2-rec (inc now) maxE))
      (recur (inc now) maxE)
      )
    0
    )
  )

;; Решение хвостовой рекурсией
(defn task2-rec-tail [now accum maxE]
  (if (<= now maxE)
    (if (=
          (sumMultedDigOfNumber now)
          now
          )
         (recur (inc now) (+ now accum) maxE)
         (recur (inc now) accum maxE)
         )
    accum
    )
  )

;; Рещение в виде Ленивых списков
(defn task2-lazy
  ([] (task2-lazy 10000))
  ([x] (reduce (fn [acc n] (if (= (sumMultedDigOfNumber n) n) (+ acc n) acc)) (range x))
   )
  )
```

## Вывод

Во время выполнения над лаборатнорной работы я базово познакомился с Clojure, научился базовым вещам,
таким как свертка, фильтрации и создание списков.

Так же приблизительно начал понимать, насколько же большая и непонятная для меня область функциональных языков программирования.