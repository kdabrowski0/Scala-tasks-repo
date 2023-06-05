package lab03

object zad_03 extends App {
    def reverse(napis: String): String = {
    //@annotation.tailrec
    def pomocnicza(napiss: String, acc: String): String = {
        if(napiss.isEmpty) acc
        else pomocnicza(napiss.tail, s"${napiss.head}$acc")//head to pierwszy element a tail to cała reszta oprocz pierwszego
    }
    pomocnicza(napis, "")
}

    def zad_03(napis: String): Unit = {
    //Napisz funkcje reverse, która dla podanego napisu zwraca odwrócony napis
    //Wykorzystaj operacje head i tail na napisach
    val odwrócony = reverse(napis)
    println(s"$napis od konca to $odwrócony")
    }
    println(zad_03("xddd"))
}
