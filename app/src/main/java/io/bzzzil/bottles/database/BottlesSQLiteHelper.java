package io.bzzzil.bottles.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import io.bzzzil.bottles.R;

public class BottlesSQLiteHelper extends SQLiteOpenHelper {

    /**
     * Database name
     */
    private static final String DATABASE_NAME = "bottles.db";

    /**
     * Database version
     */
    private static final int DATABASE_VERSION = 17;

    public BottlesSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        BottlesTable.onCreate(db);
        CountriesTable.onCreate(db);
        insertCountries(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        BottlesTable.onUpgrade(db, oldVersion, newVersion);
        CountriesTable.onUpgrade(db, oldVersion, newVersion);
        insertCountries(db);
    }

    private void insertCountries(SQLiteDatabase db)
    {
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AF', 'Afghanistan', " + R.drawable.afghanistan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AL', 'Albania', " + R.drawable.albania + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('DZ', 'Algeria', " + R.drawable.algeria + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AD', 'Andorra', " + R.drawable.andorra + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AO', 'Angola', " + R.drawable.angola + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AG', 'Antigua and Barbuda', " + R.drawable.antigua_and_barbuda + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AR', 'Argentina', " + R.drawable.argentina + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AM', 'Armenia', " + R.drawable.armenia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AU', 'Australia', " + R.drawable.australia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AT', 'Austria', " + R.drawable.austria + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AZ', 'Azerbaijan', " + R.drawable.azerbaijan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BS', 'Bahamas', " + R.drawable.bahamas + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BH', 'Bahrain', " + R.drawable.bahrain + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BD', 'Bangladesh', " + R.drawable.bangladesh + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BB', 'Barbados', " + R.drawable.barbados + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BY', 'Belarus', " + R.drawable.belarus + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BE', 'Belgium', " + R.drawable.belgium + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BZ', 'Belize', " + R.drawable.belize + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BJ', 'Benin', " + R.drawable.benin + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BT', 'Bhutan', " + R.drawable.bhutan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BO', 'Bolivia', " + R.drawable.bolivia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BA', 'Bosnia and Herzegovina', " + R.drawable.bosnia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BW', 'Botswana', " + R.drawable.botswana + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BR', 'Brazil', " + R.drawable.brazil + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VG', 'British Virgin Islands', " + R.drawable.british_virgin_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BN', 'Brunei', " + R.drawable.brunei + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BG', 'Bulgaria', " + R.drawable.bulgaria + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BF', 'Burkina Faso', " + R.drawable.burkina_faso + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BI', 'Burundi', " + R.drawable.burundi + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KH', 'Cambodia', " + R.drawable.cambodia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CM', 'Cameroon', " + R.drawable.cameroon + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CA', 'Canada', " + R.drawable.canada + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CV', 'Cape Verde', " + R.drawable.cape_verde + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CF', 'Central African Republic', " + R.drawable.central_african_republic + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TD', 'Chad', " + R.drawable.chad + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CL', 'Chile', " + R.drawable.chile + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CN', 'China', " + R.drawable.china +  ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CX', 'Christmas Island', " + R.drawable.christmas_island + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CO', 'Colombia', " + R.drawable.colombia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CD', 'Congo (Democratic Republic)', " + R.drawable.congo_democratic_republic + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CG', 'Congo (Republic)'," + R.drawable.congo_republic + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KM', 'Comoros', " + R.drawable.comoros + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CK', 'Cook Islands', " + R.drawable.cook_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CR', 'Costa Rica', " + R.drawable.costa_rica + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CI', 'Cote D''Ivoire', " + R.drawable.cote_divoire + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('HR', 'Croatia', " + R.drawable.croatia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CU', 'Cuba', " + R.drawable.cuba + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CY', 'Cyprus', " + R.drawable.cyprus + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CZ', 'Czech Republic', " + R.drawable.czech_republic + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('DK', 'Denmark', " + R.drawable.denmark + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('DJ', 'Djibouti', " + R.drawable.djibouti + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('DM', 'Dominica', " + R.drawable.dominica + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('DO', 'Dominican Republic', " + R.drawable.dominican_republic + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('EC', 'Ecuador', " + R.drawable.ecuador + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('EG', 'Egypt', " + R.drawable.egypt + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SV', 'El Salvador', " + R.drawable.el_salvador + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GQ', 'Equatorial Guinea', " + R.drawable.equatorial_guinea + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ER', 'Eritrea', " + R.drawable.eritrea + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('EE', 'Estonia', " + R.drawable.estonia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ET', 'Ethiopia', " + R.drawable.ethiopia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('FK', 'Falkland Islands', " + R.drawable.falkland_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('FO', 'Faroe Islands', " + R.drawable.faroe_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('FJ', 'Fiji', " + R.drawable.fiji + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('FI', 'Finland', " + R.drawable.finland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('FR', 'France', " + R.drawable.france + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PF', 'French Polynesia', " + R.drawable.french_polynesia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GA', 'Gabon', " + R.drawable.gabon + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GM', 'Gambia', " + R.drawable.gambia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GE', 'Georgia', " + R.drawable.georgia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('DE', 'Germany', " + R.drawable.germany + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GH', 'Ghana', " + R.drawable.ghana + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GI', 'Gibraltar', " + R.drawable.gibraltar + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GR', 'Greece', " + R.drawable.greece + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GL', 'Greenland', " + R.drawable.greenland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GD', 'Grenada', " + R.drawable.grenada + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GU', 'Guam', " + R.drawable.guam + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GT', 'Guatemala', " + R.drawable.guatemala + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GN', 'Guinea', " + R.drawable.guinea + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GW', 'Guinea-Bissau', " + R.drawable.guinea_bissau + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GY', 'Guyana', " + R.drawable.guyana + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('HT', 'Haiti', " + R.drawable.haiti + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('HN', 'Honduras', " + R.drawable.honduras + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('HK', 'Hong Kong', " + R.drawable.hong_kong + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('HU', 'Hungary', " + R.drawable.hungary + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IS', 'Iceland', " + R.drawable.iceland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IN', 'India', " + R.drawable.india + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ID', 'Indonesia', " + R.drawable.indonesia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IR', 'Iran', " + R.drawable.iran + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IQ', 'Iraq', " + R.drawable.iraq + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IE', 'Ireland', " + R.drawable.ireland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IL', 'Israel', " + R.drawable.israel + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IT', 'Italy', " + R.drawable.italy + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('JM', 'Jamaica', " + R.drawable.jamaica + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('JP', 'Japan', " + R.drawable.japan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('JO', 'Jordan', " + R.drawable.jordan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KZ', 'Kazakhstan', " + R.drawable.kazakhstan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KE', 'Kenya', " + R.drawable.kenya + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KI', 'Kiribati', " + R.drawable.kiribati + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KP', 'Korea, North', " + R.drawable.korea_north + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KR', 'Korea, South', " + R.drawable.korea_south + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KW', 'Kuwait', " + R.drawable.kuwait + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KG', 'Kyrgyzstan', " + R.drawable.kyrgyzstan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LA', 'Laos', " + R.drawable.laos + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LV', 'Latvia', " + R.drawable.latvia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LB', 'Lebanon', " + R.drawable.lebanon + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LS', 'Lesotho', " + R.drawable.lesotho + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LR', 'Liberia', " + R.drawable.liberia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LY', 'Libya', " + R.drawable.libya + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LI', 'Liechtenstein', " + R.drawable.liechtenstein + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LT', 'Lithuania', " + R.drawable.lithuania + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LU', 'Luxembourg', " + R.drawable.luxembourg + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MO', 'Macau', " + R.drawable.macau + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MK', 'Macedonia', " + R.drawable.macedonia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MG', 'Madagascar', " + R.drawable.madagascar + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MW', 'Malawi', " + R.drawable.malawi + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MY', 'Malaysia', " + R.drawable.malaysia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MV', 'Maldives', " + R.drawable.maldives + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ML', 'Mali', " + R.drawable.mali + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MT', 'Malta', " + R.drawable.malta + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MH', 'Marshall Islands', " + R.drawable.marshall_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MQ', 'Martinique', " + R.drawable.martinique + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MR', 'Mauritania', " + R.drawable.mauritania + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MU', 'Mauritius', " + R.drawable.mauritius + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MX', 'Mexico', " + R.drawable.mexico + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('FM', 'Micronesia', " + R.drawable.micronesia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MD', 'Moldova', " + R.drawable.moldova + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MC', 'Monaco', " + R.drawable.monaco + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MN', 'Mongolia', " + R.drawable.mongolia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MS', 'Montserrat', " + R.drawable.montserrat + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ME', 'Montenegro', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MA', 'Morocco', " + R.drawable.morocco + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MZ', 'Mozambique', " + R.drawable.mozambique + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MM', 'Myanmar (Burma)', " + R.drawable.myanmar + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NA', 'Namibia', " + R.drawable.namibia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NR', 'Nauru', " + R.drawable.nauru + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NP', 'Nepal', " + R.drawable.nepal + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NL', 'Netherlands', " + R.drawable.netherlands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AN', 'Netherlands Antilles', " + R.drawable.netherlands_antilles + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NZ', 'New Zealand', " + R.drawable.new_zealand + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NI', 'Nicaragua', " + R.drawable.nicaragua + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NE', 'Niger', " + R.drawable.niger + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NG', 'Nigeria', " + R.drawable.nigeria + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NU', 'Niue', " + R.drawable.niue + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NF', 'Norfolk Island', " + R.drawable.norfolk_island + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NO', 'Norway', " + R.drawable.norway + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('OM', 'Oman', " + R.drawable.oman + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PK', 'Pakistan', " + R.drawable.pakistan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PW', 'Palau', " + R.drawable.palau + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PA', 'Panama', " + R.drawable.panama + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PG', 'Papua New Guinea', " + R.drawable.papua_new_guinea + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PY', 'Paraguay', " + R.drawable.paraguay + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PE', 'Peru', " + R.drawable.peru + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PH', 'Philippines', " + R.drawable.philippines + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PN', 'Pitcairn Islands', " + R.drawable.pitcairn_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PL', 'Poland', " + R.drawable.poland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PT', 'Portugal', " + R.drawable.portugal + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PR', 'Puerto Rico', " + R.drawable.puerto_rico + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('QA', 'Qatar', " + R.drawable.qatar + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('RO', 'Romania', " + R.drawable.romania + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('RU', 'Russia', " + R.drawable.russian_federation + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('RW', 'Rwanda', " + R.drawable.rwanda + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KN', 'Saint Kitts and Nevis', " + R.drawable.saint_kitts_and_nevis + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LC', 'Saint Lucia', " + R.drawable.saint_lucia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VC', 'Saint Vincent and the Grenadines', " + R.drawable.saint_vicent_and_the_grenadines + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('WS', 'Samoa', " + R.drawable.samoa + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SM', 'San Marino', " + R.drawable.san_marino + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ST', 'Sao Tome and Principe', " + R.drawable.sao_tome_and_principe + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SA', 'Saudi Arabia', " + R.drawable.saudi_arabia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SN', 'Senegal', " + R.drawable.senegal + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('RS', 'Serbia', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SC', 'Seychelles', " + R.drawable.seychelles + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SL', 'Sierra Leone', " + R.drawable.sierra_leone + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SG', 'Singapore', " + R.drawable.singapore + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SK', 'Slovakia', " + R.drawable.slovakia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SI', 'Slovenia', " + R.drawable.slovenia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SB', 'Solomon Islands', " + R.drawable.solomon_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SO', 'Somalia', " + R.drawable.somalia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ZA', 'South Africa', " + R.drawable.south_africa + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ES', 'Spain', " + R.drawable.spain + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LK', 'Sri Lanka', " + R.drawable.sri_lanka + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SD', 'Sudan', " + R.drawable.sudan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SR', 'Suriname', " + R.drawable.suriname + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SZ', 'Swaziland', " + R.drawable.swaziland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SE', 'Sweden', " + R.drawable.sweden + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CH', 'Switzerland', " + R.drawable.switzerland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SY', 'Syria', " + R.drawable.syria + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TW', 'Taiwan', " + R.drawable.taiwan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TJ', 'Tajikistan', " + R.drawable.tajikistan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TZ', 'Tanzania', " + R.drawable.tanzania + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TH', 'Thailand', " + R.drawable.thailand + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TL', 'Timor-Leste (East Timor)', " + R.drawable.timor_leste + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TG', 'Togo', " + R.drawable.togo + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TO', 'Tonga', " + R.drawable.tonga + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TT', 'Trinidad and Tobago', " + R.drawable.trinidad_and_tobago + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TN', 'Tunisia', " + R.drawable.tunisia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TR', 'Turkey', " + R.drawable.turkey + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TM', 'Turkmenistan', " + R.drawable.turkmenistan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TC', 'Turks and Caicos Islands', " + R.drawable.turks_and_caicos_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TV', 'Tuvalu', " + R.drawable.tuvalu + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('UG', 'Uganda', " + R.drawable.uganda + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('UA', 'Ukraine', " + R.drawable.ukraine + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AE', 'United Arab Emirates', " + R.drawable.united_arab_emirates + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GB', 'United Kingdom', " + R.drawable.united_kingdom + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('US', 'United States of America', " + R.drawable.united_states_of_america+ ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('UY', 'Uruguay', " + R.drawable.uruguay + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VI', 'U.S. Virgin Islands', " + R.drawable.us_virgin_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('UZ', 'Uzbekistan', " + R.drawable.uzbekistan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VU', 'Vanuatu', " + R.drawable.vanuatu + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VA', 'Vatican City', " + R.drawable.vatican_city + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VE', 'Venezuela', " + R.drawable.venezuela + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VN', 'Vietnam', " + R.drawable.vietnam + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('YE', 'Yemen', " + R.drawable.yemen + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ZM', 'Zambia', " + R.drawable.zambia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ZW', 'Zimbabwe', " + R.drawable.zimbabwe + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CC', 'Cocos (Keeling) Islands', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('HM', 'Heard Island and McDonald Islands', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NC', 'New Caledonia', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('YT', 'Mayotte', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PM', 'Saint Pierre and Miquelon', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('WF', 'Wallis and Futuna', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TF', 'French Southern and Antarctic Lands', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BV', 'Bouvet Island', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TK', 'Tokelau', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GG', 'Guernsey', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IM', 'Isle of Man', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('JE', 'Jersey', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AI', 'Anguilla', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BM', 'Bermuda', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IO', 'British Indian Ocean Territory', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KY', 'Cayman Islands', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SH', 'Saint Helena', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GS', 'South Georgia & South Sandwich Islands', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MP', 'Northern Mariana Islands', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AS', 'American Samoa', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('UM', 'Baker Island', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GF', 'French Guiana', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GP', 'Guadeloupe', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('RE', 'Reunion', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AX', 'Aland', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AW', 'Aruba', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SJ', 'Svalbard', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AC', 'Ascension', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TA', 'Tristan da Cunha', " + R.drawable.no_flag + ")");
    }
}
