package com.example.allholidayscalendar.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.allholidayscalendar.App
import com.example.allholidayscalendar.Countries
import com.example.allholidayscalendar.domain.Interactor

class ChooseFragmentViewModel: ViewModel() {
    val countriesListLiveData = MutableLiveData<List<Countries>>()
    private var interactor: Interactor = App.instance.interactor
    init{
        val countries = interactor.getCountries()
        countriesListLiveData.postValue(countries)
    }
}

//MVVM реализация в проекте.
//1 экран - одна вью модель. Вью модель содержит данные для конкретного эекрана. У нас на экране
//(фрагмент) загружается база с праздниками, она помещается в ресайклер

// MainRepository - помещаем базу с нашими странами (флаг + название)
// Логику работы мы помещаем в класс Interactor, это связующее звено между нашей базой и вью моделью.
// Он принимающет в параметры экземпляр MainRepository и содержит функцию, возвращающую эту базу праздников.
// А передавать эту базу нам нужно во вью модель. Что происходит во вью модели:

// 1. Мы создаём переменную типа MutableLiveData - это наш обозреваемый объект, на него
// будет подписан наш фрагмент
// 2. Мы создаём переменную, куда помещаем наш интерактор, созданный ранее в классе App
// 3. В блоке init мы момещаем в переменную базу с нашими странами, и передаём эту переменную
// в наш обозреваемый объект

// Далее идём в наш ChooseFragment и инициализируем лениво вью модель.
// Создаём переменную, куда будет класть нашу базу, чтобы у нас не сломался поиск (countriesDataBase)
// Используя backing field проверяем, совпадают ли получаемые данные с теми, что есть в countriesDataBase.
// backing field позволяет обратиться к полю используя геттер и сеттер. В данном случае мы:
// 1. Задаём значение "set(value)" value - это то, что приходит к нам.
// 2. Если данные совпадают, то мы выходим из функции и ничего не происходит
// 3. Если нет, задаём новое значение нашему полю (field). field - обращение к полю, value - значение,
// которое мы получаем

// Мы в методе onViewCreated обращаемся к экземпляру вьюмодели, происходит её инициализация (тк это
// была ленивая инициализация) и подписываемся на обозреваемый объект, в лямбде функции помещаем то, что мы
// получили в обозреваемом объекте в нашу переменную
