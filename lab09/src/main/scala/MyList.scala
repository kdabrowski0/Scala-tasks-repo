package lab09

sealed trait MyList[+A]
case object Empty extends MyList[Nothing]
case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]

object MyList {

  def head[A](list: MyList[A]): A = list match {
    case Cons(h, tl) => h
    case _ => throw IllegalArgumentException("Head of an empty MyList")
  }


  // wynik: MyList-a zawierająca wszystkie elementy poza pierwszym
  def tail[A](list: MyList[A]): MyList[A] = list match {
    case Cons(h, tl) => tl
    case _ => throw IllegalArgumentException("Tail of an empty MyList")
  }

  // wynik: długość MyList-y będącej argumentem
  def length[A](list: MyList[A]): Int = list match {
    case Empty => 0
    case Cons(h, tl) => 1 + length(tl)
  }

  // wynik: MyList-a zawierająca wszystkie elementy poz n początkowymi
  def drop[A](list: MyList[A], n: Int): MyList[A] = list match {
    case Empty => Empty
    case Cons(h, tl) => if (n == 0) list else drop(tl, n - 1)
  }

  // wynik: „odwrócony” argument
  def reverse[A](list: MyList[A]): MyList[A] = list match {
    case Empty => Empty
    case Cons(h, tl) => append(reverse(tl), Cons(h, Empty))
  }

  // wynik: argument po odrzuceniu początkowych elementów spełniających p
  def dropWhile[A](l: MyList[A])(p: A => Boolean): MyList[A] = l match {
    case Empty => Empty
    case Cons(h, tl) => if (p(h)) dropWhile(tl)(p) else l
  }

  // wynik: połączone MyList-y list1 oraz list2
  def append[A](list1: MyList[A], list2: MyList[A]): MyList[A] = list1 match {
    case Empty => list2
    case Cons(h, tl) => Cons(h, append(tl, list2))
  }

  // wynik: MyList-a składająca się ze wszystkich alementów argumentu, poza ostatnim
  def allButLast[A](list: MyList[A]): MyList[A] = list match {
    case Empty => Empty
    case Cons(h, Empty) => Empty
    case Cons(h, tl) => Cons(h, allButLast(tl))
  }

}

@main def listy: Unit = {
  val l1 = Cons(1, Cons(2, Cons(3, Empty)))
  val l2 = Cons(4, Cons(5, Cons(6, Empty)))

  val res = MyList.head(l1)
  println(s"MyList.head($l1) == $res")
  // println(MyList.head(Empty)) // spowoduje „wyjątek”
}
