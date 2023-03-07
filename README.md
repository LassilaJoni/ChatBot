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
host=jdbc:mysql://10.114.34.16:6033/app_db  
password=admin  
username=admin  
Vaatii metropolia vpn yhteyden  
Sovelluksen sisällä oletus kirjautumistiedot ovat admin : admin  
![esimerkki environment variablen asettamisesta intellij idea run configuration valikossa](https://i.imgur.com/XPoav1a.png)  
Esimerkki environment variablen asettamisesta intellij idea run configuration valikossa