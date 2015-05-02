Feature: dodawanie incydentu

  Scenario: Powinien dodać incydent
	Given Użytkownik w roli admin
	Given Dane incydentu: Typ incydentu: prosty,  Opis incydentu: nowy, Ulica, numer domu/mieszkania: ulica, Kod, Miasto: miasto
	When Utwórz incydent
	Then Incydent powinien zapisać się w bazie 