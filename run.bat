:: запуск дефолтного профиля, можно без -Pdefault, так как у этого профиля стоит activeByDefault=true
mvn -Pdefault clean compile assembly:single 
:: запуск профиля oversize
mvn -Poversize clean compile assembly:single