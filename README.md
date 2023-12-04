# FoodStoreApp
 Пет проект - мобильное приложение некоторого ресторана с заказом еды, в котором можно просматривать меню.


  
 ## Стек технологий
 - Kotlin <br>
 - MVVM, CleanArchitecture <br>
 - Retrofit2, OkHttp3, Coroutines, SharedPreferences, LiveData <br>
- CollapsingToolbarLayout, Picasso

- Для кеширования ленты использована библиотека Room в ветке  <a href="https://github.com/sssofi0101/FoodStoreApp/tree/room">room</a>
 ## Реализованный функционал
 - Выбор города из выпадающего списка, а также его запоминание (SharedPreferences)
 - Прокручивающаяся область рекламных баннеров (RecyclerView)
 - CollapsingToolbarLayout - при прокрутке вниз область с баннерами скрывается
 - Просмотр меню по категориям - фильтрам (элементы Chip)
 - Отображение самого меню (использовано бесплатное API https://www.themealdb.com/api.php)
 - Нижнее меню навигации (BottomNavigationBar) с тремя вкладками, где первая - просмотр меню, остальные вкладки - заглушки
 - Кеширование ленты: при повторном открытии без доступа в интернет меню загружается из кеша (Room)
<div style="display">
<img src="https://github.com/sssofi0101/FoodStoreApp/raw/pictures/images/screenshot1.jpg" width = "200">
<img src="https://github.com/sssofi0101/FoodStoreApp/raw/pictures/images/screenshot2.jpg" width = "200">
<img src="https://github.com/sssofi0101/FoodStoreApp/raw/pictures/images/screenshot3.jpg" width = "200">
</div>

<br> <br> <br> <br> <br> Приложение для тестового задания Hammer Systems

