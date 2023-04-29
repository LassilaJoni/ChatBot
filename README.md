# CHATBOT
Emil, Miro, Joni

## Visio

Chatbotin visio oli luoda kevyt ja helppokäyttöinen chattirobotti, jonka toiminnallisuutta voi räätälöidä käyttäjän toimesta. Suunnattu pienyrityksille, ilmainen Chatbottimme on hyvin kilapilukykyinen.

## Kehitysympäristö

Chatbot on javalla (Jdk19) ohjelmoitu maven projekti, joka käyttää kirjastoinaan Javafx, Junit, JaCoCo ja MySQLConnector. Projektin versionhallintana on git, ja repository sijaitsee Metropolian gitlab palvelimella.

## Asennus + konfiguraatio

Kloonaa gitlab repository, ja rakenna mavenilla (vaatii jdk19). Sovelluksen oikein toimimiseksi järjestelmän
Environment variablejen pitää sisältää host, username ja password (jotka ovat sql palvelimen yhdistämiseen vaadittavat tiedot)
Toimivat esimerkit / tämän hetkiset Environment variablet:  
`password=ds5UCGMsdtFhmLvV;url=jdbc:mysql://mysql.metropolia.fi/miromar;username=miromar`
Vaatii metropolia vpn yhteyden  
Sovelluksen sisällä oletus kirjautumistiedot ovat admin : admin  
![esimerkki environment variablen asettamisesta intellij idea run configuration valikossa](https://i.imgur.com/XPoav1a.png)  
Esimerkki environment variablen asettamisesta intellij idea run configuration valikossa

## Jenkins
http://10.114.34.4:8080/
Tunnukset:
Username: admin
password: admin
