package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    return when (arg) {
        is String -> if(arg == "Hello") "world" else "Say what?"
        0 -> "zero"
        1 -> "one"
        is Int -> if(arg in 2 .. 10) "low number" else "a number"
        else -> {
            "I don't understand"
        }
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int {
    return lhs + rhs
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int {
    return lhs - rhs
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int): Int {
    return op(lhs, rhs)
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String = "[Person firstName:${firstName} lastName:${lastName} age:${age}]"
}

// write a class "Money"
class Money(val amount: Int, val currency: String) {
    val currencyTypes = listOf("USD", "EUR", "CAN", "GBP")
    init {
        if (amount < 0) {
            throw IllegalArgumentException("Amount can not be less than 0")
        } else if (currency !in currencyTypes) {
            throw IllegalArgumentException("Currency type must be either USD, EUR, CAN, or GBP")
        }
    }

    fun convert(toCurrencyType: String): Money {
        if (currency == "USD") {
            return when (toCurrencyType) {
                "USD" -> Money(amount, toCurrencyType)
                "EUR" -> Money(amount / 2 * 3, toCurrencyType)
                "CAN" -> Money(amount / 4 * 5, toCurrencyType)
                else -> Money(amount / 2, toCurrencyType)
            }
        } else if (currency == "EUR") {
            return when (toCurrencyType) {
                "EUR" -> Money(amount, toCurrencyType)
                "USD" -> Money(amount / 3 * 2, toCurrencyType)
                "CAN" -> Money(amount / 6 * 5, toCurrencyType)
                else -> Money(amount / 3, toCurrencyType)
            }
        } else if (currency == "CAN") {
            return when (toCurrencyType) {
                "CAN" -> Money(amount, toCurrencyType)
                "EUR" -> Money(amount / 5 * 6, toCurrencyType)
                "USD" -> Money(amount / 5 * 4, toCurrencyType)
                else -> Money(amount / 5 * 2, toCurrencyType)
            }
        } else {
            return when (toCurrencyType) {
                "GBP" -> Money(amount, toCurrencyType)
                "EUR" -> Money(amount * 3, toCurrencyType)
                "CAN" -> Money(amount * 5 / 2, toCurrencyType)
                else -> Money(amount * 2, toCurrencyType)
            }
        }
    }

    operator fun plus(other: Money): Money {
        return Money(this.amount + other.convert(currency).amount, currency)
    }
}
