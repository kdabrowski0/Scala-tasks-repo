package lab03

object zad_04 extends App {
    def IntToBin(liczba: Int): Int = {
    @annotation.tailrec
    def pomocnicza(liczba: Int, acc: Int): Int = {
        if (liczba == 0) acc
        else pomocnicza(liczba / 2, acc * 10 + liczba % 2)
    }  
    pomocnicza(liczba,0)
    
    
}
    def zad_04(liczba: Int): Unit = {
    require(liczba>=0)
    //Napisz funkcję IntToBin, która dla podanej liczby naturalnej zwróci jej reprezentację w systemie binarnym
    val binarna = IntToBin(liczba)
    println(s"$liczba w systemie binarnym jest zapisywana jako $binarna")
}
    println(zad_04(5))
}
