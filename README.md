# Acontece

Este aplicativo utiliza o Retrofit para consumir uma REST API e exibir uma lista de eventos. Para obter maior controle sobre o lifecyle, o projeto segue a recomendação da Google ao trabalhar com LiveData e ViewModels. Com o intuito de facilitar os testes e deixar o código mais conciso, foi utilizado a library MockK, pois apresenta uma sintaxe muito mais próxima do Kotlin que o Mockito, por exemplo. 

<b>Arquitetura:</b><br>
MVVM com Architecture Components

<b>Tecnologias utilizadas:</b><br>
MockK (testes unitários)<br>
Databinding<br>
Rx<br>
Lottie (animações)<br>
Retrofit<br>
Dagger<br>
