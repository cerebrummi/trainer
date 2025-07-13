package vokabeltrainer.common;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class ApplicationTime
{

   private ApplicationTime()
   {
      // nothing
   }

   enum Direction
   {
      S, N, E, W
   }
   
   enum TimezoneUTC
   {
      GMT(0),
      PLUS_1(1),
      PLUS_2(2),
      PLUS_3(3),
      PLUS_4(4),
      PLUS_5(5),
      PLUS_6(6),
      PLUS_7(7),
      PLUS_8(8),
      PLUS_9(9),
      PLUS_10(10),
      PLUS_11(11),
      MINUS_1(-1),
      MINUS_2(-2),
      MINUS_3(-3),
      MINUS_4(-4),
      MINUS_5(-5),
      MINUS_6(-6),
      MINUS_7(-7),
      MINUS_8(-8),
      MINUS_9(-9),
      MINUS_10(-10),
      MINUS_11(-11),
      MINUS_12(-12);
      
      private int deltaHour;
      
      TimezoneUTC(int deltaHour)
      {
         this.deltaHour = deltaHour;
      }
   }

   enum Cities
   {
      ABERDEEN("Aberdeen", "Scotland", 57.15, Direction.N, -2.11, Direction.W), 
      ABIDJAN("Abidjan", "Ivory Coast", 5.316667, Direction.N, -4.033333, Direction.W, TimezoneUTC.GMT),
      ABU_DHABI("Abu Dhabi", "United Arab Emirates", 24.466667, Direction.N, 54.366667, Direction.E),
      ABUJA("Abuja", "Nigeria", 9.066667, Direction.N, 7.48333, Direction.E),
      Accra("Accra", "Ghana", 5.55, Direction.N, -0.2, Direction.W),
      ADAMSTOWN("Adamstown", "Pitcairn Islands", -25.066667, Direction.S, -130.1, Direction.W),
      ADELAIDE("Adelaide", "Australia", -34.9275, Direction.S, 138.6, Direction.E), 
      ADDIS_ABABA("Adis Ababa", "Ethiopia", 9.03, Direction.N, 38.74, Direction.E),
      ADEN("Aden", "Yemen", 12.8, Direction.N, 45.033333, Direction.E),
      ALGIERS("Algiers", "Algeria", 36.7325, Direction.N, 3.087222, Direction.E), 
      ALOFI("Alofi", "Niue", -19.054167, Direction.S, -169.919444, Direction.W),
      AMMAN("Amman", "Jordan", 31.949722, Direction.N, 35.932778, Direction.E),
      AMSTERDAM("Amsterdam", "Netherlands", 52.372778, Direction.N, 4.893611, Direction.E),
      ANDORRA_LA_VELLA("Andorra la Vella", "Andorra", 42.5, Direction.N, 1.5, Direction.E),
      ANKARA("Ankara", "Turkey", 39.93, Direction.N, 32.85, Direction.E),
      ANTANANARIVO("Antananarivo", "Madagascar", -18.91, Direction.S, 47.525, Direction.E),
      APIA("Apia", "Samoa", -13.833333, Direction.S, -171.75, Direction.W),
      ASHGABAT("Asgabat", "Turkmenistan", 37.9375, Direction.N, 58.38, Direction.E),
     /* ASMARA("Asmara", "Eritrea"),
      ASTANA("Astana", "Kazakhstan"),
      ASUNCION("Asunción", "Paraguay", 25.25, Direction.S, 57.67, Direction.W),
      ATAFU("Atafu", "Tokelau"),
      ATHENS("Athens", "Greece", 37.97, Direction.N, 23.72, Direction.E),
      AUCKLAND("Auckland", "New Zealand", 36.87, Direction.S, 174.75, Direction.E),
      AVARUA("Avarua", "Cook Islands"),
      
      BAGHDAD("Badhdad", "Iraq"),
      BAKU("Baku", "Azerbaijan"),
      BAMAKO("Bamako", "Mali"),
      BANDAR_SERI_BEGAWAN("Bandar Seri Begawan", "Brunei"),
      BANGKOK("Bangkok", "Thailand", 13.75, Direction.N, 100.50, Direction.E),
      BANGUI("Bangui", "Central African Republic"),
      BANJUL("Banjul", "Gambia"),
      BASSETERRE("Basseterre", "Saint Kitts and Nevis"),
      BARCELONA("Barcelona", "Spain", 41.38, Direction.N, 2.15, Direction.E),
      BEIJING("Beijing", "China", 39.92, Direction.N, 116.42, Direction.E),
      BEIRUT("Beirut", "Lebanon"),
      BELM("Belm", "Brazil", 1.47 , Direction.S, 48.48, Direction.W),
      BELFAST("Belfast", "Northern Ireland", 54.62, Direction.N, 5.93, Direction.W),
      BELGRADE("Belgrade", "Serbia", 44.87, Direction.N, 20.53, Direction.E),
      BELMOPAN("Belmopan", "Belize"),
      BERLIN("Berlin", "Germany", 52.50, Direction.N, 13.42, Direction.E),
      BERN("Bern", "Switzerland"),
      BIRMINGHAM("Birmingham", "England", 52.42, Direction.N, 1.92, Direction.W),
      BISHKEK("Bishkek", "Kyrgyzstan"),
      BISSAU("Bissau", "Kyrgyzstan"),
      BLOEMTONTEIN("Blomfontein", "South Africa"),
      BOGOTA("Bogota", "Colombia", 4.53, Direction.N, 74.25, Direction.W),
      BOMBAY("Bombay", "India", 19.00, Direction.N, 72.80, Direction.E),
      BORDEAUX("Bordeaux", "France", 44.83, Direction.N, 0.52, Direction.W),
      BRADES("Brades", "Montserrat"),
      BRASILIA("Brasilia", "Brazil"),
      BRATISLAVA("Bratislava", "Slovakia"),
      BARZZAVILLE("Brazzaville", "Congo"),
      BREMEN("Bremen", "Germany", 53.08, Direction.N, 8.82, Direction.E),
      BRISBANE("Brisbane", "Australia", 27.48, Direction.S, 153.13, Direction.E),
      BRIDGETOWN("Bridgetown", "Barbados"),
      BRISTOL("Bristol", "England", 51.47, Direction.N, 2.58, Direction.W),
      BRUSSELS("Brussels", "Belgium", 50.87, Direction.N, 4.37, Direction.E),
      BUCHAREST("Bucharest", "Romania", 44.42, Direction.N, 26.12, Direction.E),
      BUDAPEST("Budapest", "Hungary", 47.50, Direction.N, 19.8, Direction.E),
      BUENOS_AIRES("Buenos Aires", "Argentina", 34.58, Direction.S, 58.37, Direction.W),
      BUJUMBURA("Bujumbura", "Burundi"),
      
      CAIRO("Cairo", "Egypt",30.3, Direction.N, 31.35, Direction.E),
      CALCUTTA("Calcutta", "India", 22.57, Direction.N, 88.40, Direction.E),
      CANBERRA("Canberra", "Australia"),
      CANTON("Canton", "China" , 23.12, Direction.N, 113.25, Direction.E),
      CAPE_TOWN("Cape Town", "South Africa", 33.92, Direction.S, 18.37, Direction.E),
      CARACAS("Caracas", "Venezuela", 10.47, Direction.N, 67.03, Direction.W),
      CETINJE("Cetinje", "Montenegro", 42.389444, Direction.N, 18.924722, Direction.E),
      CHARLOTTE_AMALIE("Charlotte Amalie", "United State Virgin Islands"),
      CASTRIES("Castries", "Saint Lucia"),
      CAYENNE("Cayenne", "French Guiana", 4.82, Direction.N, 52.30, Direction.W),
      CHIHUAHUA("Chihuahua", "Mexico", 28.62, Direction.N, 106.08, Direction.W),
      CHISINAU("Chișinău", "Moldova"),
      CHONGQING("Chongqing", "China", 29.77, Direction.N, 106.57, Direction.E),
      CHOCKBURN_TOWN("Chockburn Town", "Turks and Caicos Islands"),
      COLOMBO("Colombo", "Sri Lanka"),
      CONAKRY("Conakry", "Guinea"),
      COPENHAGEN("Copenhagen", "Denmark", 55.67, Direction.N, 12.57, Direction.E),
      COTONOU("Cotonou", "Benin"),
      CARDOBA("Càrdoba", "Argentina", 31.47, Direction.S, 64.17, Direction.W),
                  
      DAKAR("Dakar", "Senegal", 14.67, Direction.N, 17.47, Direction.W),
      DAMASKUS("Damaskus", "Syria"),
      DAHKA("Dahka", "Bangladesh"),
      DARWIN("Darwin", "Australia", 12.47, Direction.S, 130.85, Direction.E),
      DIEGO_GARCIA("Diego Carcia", "British Indian Ocean Territory"),
      DILI("Dili", "East Timor"),
      DJIBOUTI("Djibouti", "Djibouti", 11.50, Direction.N, 43.05, Direction.E),
      DODOMA("Dodoma", "Tanzania"),
      DOHA("Doha", "Qutar"),
      DOUGLAS("Douglas", "Isle of Man"),
      DUBLIN("Dublin", "Ireland", 53.33, Direction.N, 6.25, Direction.W),
      DURBAN("Durban", "South Africa", 29.88, Direction.S, 30.88, Direction.E),
      DUSHANBE("Dushanbe", "Tajikistan"),
      
      EDINBURGH("Edinburgh", "Scotland", 55.92, Direction.N, 3.17, Direction.W),
      EDINBURGH_OF_THE_SEVEN_SEAS("Edinburgh of the Seven Seas", "Tristan da Cunha"),
      EPISKOPI_CANTONMENT("Episkopi Cantonment", "Akrotiri / Dhekelia"),
      
      FAKAOFO("Fakaofo", "Tokelau"),
      FLYING_FISH_COVE("Flying Fish Cove", "Christmas Island"),
      FRANKFURT("Frankfurt", "Germany", 50.12, Direction.N, 8.68, Direction.E),
      FREETOWN("Freetown", "Sierra Leone"),
      FUNAFUTI("Funafuti", "Tuvalu"),
      
      GABORONE("Baborone", "Botswana"),
      GEORGE_TOWN("George Town", "Cayman Islands"),
      GEORGETOWN_AI("Georgetown", "Ascension Island"),
      GEORGETOWN_GU("Georgetown", "Guyana", 6.75,Direction.N,58.25,Direction.W),
      GIBRALTAR("Gibralta", "Gibralta"),
      GITEGA("Gitega", "Burundi"),
      GLASGOW("Glasgow", "Scotland", 55.83,Direction.N, 4.25,Direction.W),
      GUATEMALA_CITY("Guatemala City", "Guatemala",14.62,Direction.N,90.52,Direction.W),
      GUAYAQUIL("Guayaquil", "Ecuador", 2.17,Direction.S,79.93,Direction.W),
      GUSTAVIA("Gustavia", "Saint Barthélemy"),
      
      HAGATNA("Hagåtña", "Guam"),
      HAMBURG("Hamburg", "Germany", 53.55,Direction.N,10.3, Direction.E),
      HAMILTON("Hamilton", "Bermuda"),
      HAMMERFEST("Hammerfest", "Norway", 70.63,Direction.N,23.63,Direction.E),
      HANOI("Hanoi", "Vietnam"),
      HARARE("Harare", "Zimbabwe"),
      HARGEISA("Hargeisa", "Somaliland"),
      HAVANA("Havana", "Cuba", 23.13,Direction.N,82.38,Direction.W),
      HELSINKI("Helsinki", "Finland", 60.17,Direction.N,25.00,Direction.E),
      HOBART("Hobart", "Tasmania", 42.87,Direction.S,147.32,Direction.E),
      HONG_KONG("Hong Kong", "China", 22.33,Direction.N,114.18,Direction.E),
      HONIARA("Honiara", "Solomon Islands"),
      
      IQUIQUE("Iquique", "Chile", 20.17,Direction.S,70.12,Direction.W),
      IRKUTSK("Irkutsk", "Russia", 52.50,Direction.N,104.33,Direction.E),
      ISLAMABAD("Islamabad", "Pakistan"),  
      
      JAKARTA("Jakarta", "Indonesia",6.27,Direction.S,106.80,Direction.E),
      JAMESTOWN("Jamestown", "Saint Helnena"),
      JERUSALEM("Jerusalem", "Israel"),
      JOHANNESBURG("Johannesburg", "South Africa",26.20,Direction.S,28.7,Direction.E),
      JUBA("Juba", "South Sudan"),      
      
      KABUL("Kabul", "Afghanistan"),
      KAMPALLA("Kampala", "Uganda"),
      KATHMANDU("Kathmandu", "Nepal"),
      KHARTOUM("Khartoum", "Sudan"),
      KIGALI("Kigali", "Rwanda"),
      KING_EDWARD_POINT("King Edward Point", "South Georgia and South Sandwich Islands"),
      KINGSTON_JA("Kingston", "Jamaica", 17.98,Direction.N,76.82,Direction.W),
      KINGSTON_NI("Kingston", "Norfolk Island"),
      KINGSTOWN("Kingstown", "Saint Vincent and Grenadines"),
      KINSHASA("Kinshasa", "Congo", 4.30,Direction.S,15.28,Direction.E),
      KOROR_CITY("Koror City", "Palau", 7.341945, Direction.N, 134.479167, Direction.E),
      KUALA_LUMPUR("Kuala Lumpur", "Malaysia", 3.13,Direction.N,101.70,Direction.E),
      KUWAIT_CITY("Kuwait City", "Kuwait"),
      KYIV("Kyiv", "Ukrain"),
      
      LA_PAZ("La Paz", "Bolivia", 16.45,Direction.S,68.37,Direction.W),
      LAGOS("Lagos", "Nigeria"),
      Laayoune("Laayoune", "Western Sahara"),
      LEEDS("Leeds", "England", 53.75,Direction.N, 1.50,Direction.W),
      LIBREVILLE("Libreville", "Gabon"),
      LILONGWE("Lilongwe", "Malavi"),
      LIMA("Lima", "Peru", 12.00,Direction.S,77.03,Direction.W),
      LISBON("Lisbon", "Portugal", 38.73,Direction.N, 9.15,Direction.W),
      LIVERPOOL("Liverpool", "England", 53.42,Direction.N,3.00,Direction.W),
      LOBAMBA("Lobamba", "Eswatini"),
      LOME("Lomé", "Togo"),
      LONDON("London", "United Kingdom", 51.53,Direction.N,0.08,Direction.W),
      LJUBLJANA("Ljubljana", "Slovenia"),
      LUANDA("Luanda", "Angola"),
      LUSAKA("Lusaka", "Zambia"),
      LUXEMBOURG("Luxembourg", "Luxembourg"),
      LYONS("Lyons", "France", 45.75,Direction.N, 4.83,Direction.E),
            
      MADRID("Madrid", "Spain", 40.43,Direction.N, 3.70,Direction.W),
      MAJURO("Majuro", "Marshall Islands"),
      MALABO("Malabo", "Equatorial Guinea"),
      MALE("Malé", "Maldives"),
      MANAGUA("Managua", "Nicaragua"),
      MANAMA("Manama", "Bahrain"),
      MANCHESTER("Manchester", "England", 53.50,Direction.N, 2.25,Direction.W),
      MANILA("Manila", "Philippines", 14.58,Direction.N,120.95,Direction.E),
      MAPUTO("Maputo", "Mozambique"),
      MARIEHAMN("Mariehamn", "Åland Islands"),
      MARIGOT("Marigot", "Saint Martin"),
      MARSEILLES("Marseilles", "France", 43.33,Direction.N, 5.33,Direction.E),
      MASERU("Maseru", "Lesotho"),
      MATA_UTU("Mata Utu", "Wallis and Futuna"),
      MAZATL("Mazatl", "Mexico", 23.20,Direction.N,106.42,Direction.W),
      MBABANE("Mbabane", "Ewatini"),
      MECCA("Mecca", "Saudi Arabia", 21.48,Direction.N,39.75,Direction.E),
      MELBOURNE("Melbourne", "Australia", 37.78,Direction.S,144.97,Direction.E),
      MEXICO_CITY("Mexico City", "Mexico", 19.43,Direction.N,99.12,Direction.W),
      MILAN("Milan", "Italy", 45.45,Direction.N, 9.17,Direction.E),
      MINSK("Minsk", "Belarus"),
      MOGADISHU("Mogadishu", "Somalia"),
      MONACO("Monaco", "Monaco"),
      MONROVIA("Monrovia", "Liberia"),
      MONTEVIDEO("Montevideo", "Uruguay", 34.88,Direction.S,56.17,Direction.W),
      MORONI("Moroni", "Comoros"),
      MOSCOW("Moscow", "Russia", 55.75,Direction.N,37.60,Direction.E),
      MUSCAT("Muscat", "Oman"),
      MUNICH("Munich", "Germany", 48.13,Direction.N, 11.58,Direction.E),
            
      NAGASAKI("Nagasaki", "Japan", 32.80,Direction.N,129.95,Direction.E),
      NAGOYA("Nagoya", "Japan", 35.12, Direction.N,136.93,Direction.E),
      NAIROBI("Nairobi", "Kenya", 1.42,Direction.S,36.92,Direction.E),
      NANJING("Nanjing", "China", 32.05,Direction.N,118.88,Direction.E),
      NAPLES("Naples", "Italy", 40.83,Direction.N,14.25,Direction.E),
      NASSAU("Nassau", "Bahamas"),
      NAYPYIDAW("Naypyidaw", "Myanmar"),
      N_DJAMENA("N'Djamena", "Chad"),
      NEW_DELHI("New Delhi", "India", 28.58, Direction.N, 77.20, Direction.E),
      NEWCASTLE_ON_TYNE("Newcastle-on-Tyne", "England", 54.97,Direction.N,1.62,Direction.W),
      NGERULMUD("Ngerulmud", "Palau"),
      NIAMEY("Niamey", "Niger"),
      NICOSIA("Nicosia", "Cyprus"),
      NOUAKCHOTT("Nouakchott","Mauritania"),
      NOUMEA("Nouméa", "New Caledonia"),
      NUKU_ALOFA("Nukuʻalofa", "Tonga"),
      NUKUNONU("Nukunonu", "Tokelau"),
      NUSANTARA("Nusantara", "Indonesia"),
      NUUK("Nuuk", "Greenland"),
      
      ODESSA("Odessa", "Ukraine", 46.45,Direction.N,30.80,Direction.E),
      ORANJESTAD("Oranjestad", "Aruba"),
      OSAKA("Osaka", "Japan", 34.53,Direction.N,135.50,Direction.E),
      OSLO("Oslo", "Norway", 59.95,Direction.N, 10.70,Direction.E),
      OTTAWA("Ottawa", "Canada"),
      OUAGADOUGOU("Ouagadougou", "Burkina Faso"),
      
      PAGO_PAGO("Pago Pago", "American Samoa"),
      PALIKIR("Palikir", "Micronesia"),
      PANAMA_CITY("Panama City", "Panama", 8.97,Direction.N,79.53,Direction.W),
      PAPEETE("Üaüeete", "French Polynesia"),
      PARAMARIBO("Paramaribo", "Suriname", 5.75,Direction.N,55.25,Direction.W),
      PARIS("Paris", "France", 48.80,Direction.N,2.33,Direction.E),
      PHILIPSBURG("Philipsburg", "Sint Maarten"),
      PHNOM_PENH("Phnom Penh", "Cambodia"),
      PERTH("Perth", "Australia", 31.95,Direction.S,115.87,Direction.E),
      PLYMOUTH_EN("Plymouth", "England", 50.42,Direction.N, 4.8,Direction.W),
      PLYMOUTH_MO("Plymouth", "Montserrat"),
      PORT_AU_PRINCE("Port-au-Prince", "Haiti"),
      PORT_LOUIS("Port Louis", "Mauritius"),
      PORT_MORESBY("Port Moresby", "Papua New Guinea", 9.42,Direction.S,147.13,Direction.E),
      PORT_OF_SPAIN("Porf of Spain", "Trinidad and Tobago"),
      PORT_VILA("Port Vila", "Vanuatu"),
      PORTO_NOVO("Porto-Novo", "Benin"),
      PODGORICA("Podgorica", "Montenegro"),
      PRAGUE("Prague", "Czech Republic", 50.08,Direction.N,14.43,Direction.E),
      PRAIA("Praia", "Cape Verde"),
      PRETORIA("Pretoria", "South Africa"),
      PRISTINA("Pristina", "Kosovo"),
      PUTRAJAYA("Putrajaya", "Malaysia"),
      PYONGYANG("Pyongyang", "North Korea"),
      
      QUITO("Quito", "Ecuador"),
      
      RABAT("Rabat", "Marocco"),
      RAMALLAH("Ramallah", "Westjordanland"),
      RANGOON("Rangoon", "Myanmar", 16.83,Direction.N,96.00,Direction.E),
      REYKJAVIK("Reykjavik", "Iceland", 64.07,Direction.N,21.97,Direction.W),
      RIGA("Riga", "Latvia"),
      RIO_DE_JANEIRO("Rio de Janeiro", "Brazil", 22.95,Direction.S,43.20,Direction.W),
      RIYADH("Riyadh", "Saudi Arabia"),
      ROAD_TOWN("Road Town", "British Virgin Islands"),
      ROME("Rome", "Italy", 41.90,Direction.N, 12.45,Direction.E),
      ROSEAU("Roseau", "Dominica"),
      ROTHERA("Rothera", "British Antarctic Territory"),
      
      SAIPAN("Northern Mariana Islands"),
      SALVADOR("Salvador", "Brazil", 12.93,Direction.S,38.45,Direction.W),
      SAN_JOSE("San José", "Costa Rica"),
      SAN_JUAN("San Juan", "Puerto Rico"),
      SAN_MARINO("San Marino"),
      SAN_SALVADOR("San Salvador", "El Salvador"),
      SANAA("Sanaa", "Yemen"),
      SANTIAGO("Santiago", "Chile", 33.47,Direction.S,70.75,Direction.W), 
      SANTO_DOMINGO("Santo Domingo", "Dominican Republic"),
      SAO_PAULO("Sao Paulo", "Brazil", 23.52,Direction.S,46.52,Direction.W),
      SAO_TOME("São Tomé", "São Tomé and Principe"),
      SARAJEVO("Sarajevo", "Bosnia and Herzegovina"),
      SEOUL("Seoul", "South Korea"),
      SHANGHAI("Shanghai", "China", 31.17,Direction.N,121.47,Direction.E),
      SINGAPORE("Singapore", "Singapore", 1.23,Direction.N,103.92,Direction.E),
      SKOPJE("Skopje", "North Macedonia"),
      SOFIA("Sofia", "Bulgaria", 42.67,Direction.N,23.33,Direction.E),
      SOUTH_TARAWA("South Tarawa", "Kiribati"),
      SRI_JAYAWARDENEPURA_KOTTE("Sri Jayawardenepura Kotte", "Sri Lanka"),
      ST_GEORGE_S("St. George's", "Grenada"),
      ST_HELIER("St. Helier", "Jersey"),
      ST_JOHN_S("St. John's", "Antigua and Barbuda"),
      ST_PETER_PORT("St. Peter Port", "Guernsey"),
      ST_PETERSBURG("St. Petersburg", "Russia", 59.93,Direction.N,30.30,Direction.E),
      ST_PIERRE("St. Pierre", "Saint Pierre and Miquelon"),
      STANLEY("Stanley", "Falkland Islands"),
      STOCKHOLM("Stockholm", "Sweden", 59.28,Direction.N,18.5,Direction.E),
      SUCRE("Sucre", "Bolivia"),
      SUVA("Suva", "Fiji"),
      SYDNEY("Sydney", "Australia", 34.00,Direction.S,151.00,Direction.E),
      
      TAIPEI("Taipei", "Taiwan"),
      TALLINN("Tallinn", "Estonia"),
      TANANARIVE("Tananarive", "Madagascar", 18.83,Direction.S,47.55,Direction.E),
      TASHKENT("Tashkent", "Uzbekistan"),
      TBILISI("Tbilisi", "Georgia"),
      TEGUCIGALPA("Tegucigalpa", "Honduras"),
      TEHRAN("Tehran", "Iran", 35.75,Direction.N,51.75,Direction.E),
      THE_HAGUE("The Hague", "Netherlands"),
      THE_VALLEY("The Valley", "Anguilla"),
      THIMPHU("Thimphu", "Bhutan"),
      TIFARITI("Tifariti", "Western Sahara"),
      TIRANA("Tirana", "Albania"),
      TOKYO("Tokyo", "Japan", 35.67,Direction.N,139.75,Direction.E),
      TORSHAVN("Tórshavn", "Faroe Islands"),
      TRIPOLI("Tripoli", "Libya", 32.95,Direction.N,13.20,Direction.E),
      TSKHINVALI("Tskhinvali", "South Ossetia"),
      TUNIS("Tunis", "Tunisia"),
      
      ULAANBAATAR("Ulaanbaatar", "Mongolia"),
      
      VADUZ("Vaduz", "Liechtenstein"),
      VALLETTA("Valletta", "Malta"),
      VATICAN_CITY("Vatican City", "Vatican City"),
      VENICE("Venice", "Italy", 45.43,Direction.N, 12.33,Direction.E),
      VERACRUZ("Veracruz", "Mexico", 19.17,Direction.N,96.17,Direction.W),
      VICTORIA("Victoria", "Seychelles"),
      VIENNA("Vienna", "Austria", 48.23,Direction.N,16.33,Direction.E),
      VIENTIANE("Vientiane", "Laos"),
      VILNIUS("Vilnius", "Lithuania"),
      VLADIVOSTOK("Vladivostok", "Russia", 43.17,Direction.N,132.00,Direction.E),
      
      WARSAW("Warsaw", "Poland", 52.23,Direction.N,21.00,Direction.E),
      WASHINGTON_D_C("Washington, D.C.", "United States"),
      WELLINGTON("Wellington", "New Zealand", 41.28,Direction.S,174.78,Direction.E),
      WEST_ISLAND("West Island", "Cocos Islands"),
      WILLEMSTAD("Willemstad", "Curaçao"),
      WINDHOEK("Windhoek", "Namibia"),
      
      YAMOUSSOUKRO("Yamoussoukro", "Ivory Coast", 6.816111, Direction.N, -5.274167, Direction.W),
      YAOUNDE("Yaoundé", "Cameroon"),
      YAREN_DISTRICT("Yaren", "Nauru"),
      YEREVAN("Yerevan", "Armenia"),
      
      ZAGREB("Zagreb", "Croatia"),
      ZUERICH("Zürich", "Switzerland", 47.35, Direction.N, 8.52, Direction.E)*/;

      private String cityname;
      private String countryname;
      private double latitude;
      private Direction latitudeDirection;
      private double longitude;
      private Direction longitudeDirection;
      private TimezoneUTC timezoneUTC;
      private double longitudeSunsetDay; // 360° / 24 hours = 15°/hour

      Cities(String cityname, String countryname, double latitude,
            Direction latitudeDirection, double longitude,
            Direction longitudeDirection, TimezoneUTC timezoneUTC)
      {
         this.cityname=cityname;
         this.countryname=countryname;
         this.latitude=latitude;
         this.latitudeDirection=latitudeDirection;
         this.longitude=longitude;
         this.longitudeDirection=longitudeDirection;
         this.longitudeSunsetDay = (18.0 - (this.longitude/15.0)) / 24; // 18 hour for sunset; 24 hours per day
      }
      
      public LocalDateTime getShabbatStart(LocalDate friday)
      {
         if (!friday.getDayOfWeek().equals(DayOfWeek.FRIDAY))
         {
            return null;
         }
         return getShabbatSunset(friday);
      }
      
      public LocalDateTime getShabbatEnd(LocalDate saturday)
      {
         if(!saturday.getDayOfWeek().equals(DayOfWeek.SATURDAY))
         {
            return null;
         }
         return getShabbatSunset(saturday);
      }

      public LocalDateTime getShabbatSunset(LocalDate day)
      {
         double sunsetDay = ((double) day.getDayOfYear())
               + this.longitudeSunsetDay;
         double delta = (sunsetDay * 0.9856) - 3.289;
         double sinDelta = Math.sin(delta * (Math.PI / 180.0)); // using radians
         double sinDeltaDouble = Math.sin((delta * (Math.PI / 180.0)) * 2); // using
                                                                            // radians
         double part1 = sinDelta * 1.916;
         double part2 = (sinDeltaDouble * 0.020) + 282.634;
         double realLongitude = delta + part1 + part2;

         if (realLongitude > 360.0)
         {
            realLongitude -= 360.0;
         }

         double cosFromZenithRadians = Math.cos(98 * (Math.PI / 180.0)); // 90°
                                                                         // + 8°
         double sinRealLongitude = Math.sin(realLongitude * (Math.PI / 180.0));
         double cosRealLongitude = Math.cos(Math.asin(sinRealLongitude));
         double sinLatitude = Math.sin(this.latitude * (Math.PI / 180.0));
         double cosLatitude = Math.cos(this.latitude * (Math.PI / 180.0));

         double top = cosFromZenithRadians - (sinRealLongitude * sinLatitude);
         double bottom = cosRealLongitude * cosLatitude;
         double sunsetRealDay = top / bottom;

         double sunsetLocalRealDay = (Math.acos(sunsetRealDay)
               * (180.0 / Math.PI)) / 15.0;

         double rightAscension = getRightAscension(realLongitude);
         double localMeanTime = sunsetLocalRealDay + rightAscension
               - (sunsetRealDay * 0.06571) - 6.622;

         if (localMeanTime < 0.0)
         {
            localMeanTime += 24;
         }
         else if (localMeanTime > 24.0)
         {
            localMeanTime -= 24;
         }

         double utcTime = localMeanTime - this.longitude / 15; // 15° per hour
         double utcOffSet = 
         
         return null;
      }
      

      private double getUTCOffSet(Calendar date) {
          BigDecimal offSetInMillis = new BigDecimal(date.get(Calendar.ZONE_OFFSET));
          BigDecimal offSet = offSetInMillis.divide(new BigDecimal(3600000), new java.math.MathContext(2));
          return offSet.doubleValue();
      }

      private double getLocalTime(double localMeanTime, LocalDate date)
      {
          double utcOffSet = getUTCOffSet(date);
          double utcOffSetTime = utcTime.add(utcOffSet);
          return adjustForDST(utcOffSetTime, date);
      }

      private double getRightAscension(double realLongitude)
      {
         double tanL = Math.tan(realLongitude * (Math.PI / 180.0));

         double rightAscension = (Math
               .atan(tanL * (180.0 / Math.PI) * 0.91764 * (Math.PI / 180.0)))
               * (180.0 / Math.PI);

         if (rightAscension < 0.0)
         {
            rightAscension += 360.0;
         }
         else if (rightAscension > 360)
         {
            rightAscension -= 360;
         }

         double longitudeQuadrant = Math.round(realLongitude);
         double rightAscensionQuadrant = Math.round(rightAscension);

         return (rightAscension + (longitudeQuadrant - rightAscensionQuadrant))
               / 15;
      }
   }
}
