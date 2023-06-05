package lab03

object zad_01 extends App {

def ciagGeometryczny(n: Int, iloraz: Double, poczatek: Double): Double = {
    @annotation.tailrec    
    def accCiag( n: Int, iloraz: Double, poczatek: Double, acc: Double): Double = {
        if (n == 1) acc * poczatek
        else accCiag(n - 1, iloraz , poczatek, acc * iloraz)
    }
    accCiag(n, iloraz, poczatek, 1.0)   
}   
    def zad_01(n: Int, iloraz: Double, poczatek: Double): Unit = {
    require(n>=0)
    //Zdefiniuj funkcję ciągGeometryczny tak, aby zwracała
    //n-ty wyraz ciągu geometrycznego dla zadanego ilorazu i wyrazu początkowego
    val wynik = ciagGeometryczny(n, iloraz, poczatek)
    println(s"a_$n dla ciagu a_n=$poczatek*($iloraz^n-1) wynosi:$wynik")
}
    println(zad_01(4,2.0,3.0))

}
