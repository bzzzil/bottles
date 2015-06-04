package io.bzzzil.bottles.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import io.bzzzil.bottles.BottlesApplication;
import io.bzzzil.bottles.R;

public class CountriesTable {
    // Database table
    /**
     * Bottles: table name
     */
    public static final String TABLE_COUNTRIES = "countries";

    /**
     * Countries: column "id"
     */
    public static final String COLUMN_ID = "_id";

    /**
     * Countries: column "id" full specification
     */
    public static final String FULL_ID = TABLE_COUNTRIES + "._id";

    /**
     * Countries: column "name"
     */
    public static final String COLUMN_NAME = "name";

    /**
     * Countries: column "flag_resource_id"
     */
    public static final String COLUMN_FLAG_RESOURCE_ID = "flag_resource_id";

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_COUNTRIES + " ("
                + COLUMN_ID + " varchar(2) primary key, "
                + COLUMN_NAME + " text not null, "
                + COLUMN_FLAG_RESOURCE_ID + " integer not null "
                + ")");
        insertCountries(db);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(CountriesTable.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion
                        + ". ALL DATA WILL BE DESTROYED");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRIES);
        onCreate(db);
    }

    private static void insertCountries(SQLiteDatabase db)
    {
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AF', '" + BottlesApplication.getContext().getString(R.string.country_afghanistan) + "', " + R.drawable.afghanistan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AL', '" + BottlesApplication.getContext().getString(R.string.country_albania) + "', " + R.drawable.albania + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('DZ', '" + BottlesApplication.getContext().getString(R.string.country_algeria) + "', " + R.drawable.algeria + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AD', '" + BottlesApplication.getContext().getString(R.string.country_andorra) + "', " + R.drawable.andorra + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AO', '" + BottlesApplication.getContext().getString(R.string.country_angola) + "', " + R.drawable.angola + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AG', '" + BottlesApplication.getContext().getString(R.string.country_antigua_and_barbuda) + "', " + R.drawable.antigua_and_barbuda + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AR', '" + BottlesApplication.getContext().getString(R.string.country_argentina) + "', " + R.drawable.argentina + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AM', '" + BottlesApplication.getContext().getString(R.string.country_armenia) + "', " + R.drawable.armenia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AU', '" + BottlesApplication.getContext().getString(R.string.country_australia) + "', " + R.drawable.australia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AT', '" + BottlesApplication.getContext().getString(R.string.country_austria) + "', " + R.drawable.austria + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AZ', '" + BottlesApplication.getContext().getString(R.string.country_azerbaijan) + "', " + R.drawable.azerbaijan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BS', '" + BottlesApplication.getContext().getString(R.string.country_bahamas) + "', " + R.drawable.bahamas + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BH', '" + BottlesApplication.getContext().getString(R.string.country_bahrain) + "', " + R.drawable.bahrain + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BD', '" + BottlesApplication.getContext().getString(R.string.country_bangladesh) + "', " + R.drawable.bangladesh + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BB', '" + BottlesApplication.getContext().getString(R.string.country_barbados) + "', " + R.drawable.barbados + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BY', '" + BottlesApplication.getContext().getString(R.string.country_belarus) + "', " + R.drawable.belarus + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BE', '" + BottlesApplication.getContext().getString(R.string.country_belgium) + "', " + R.drawable.belgium + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BZ', '" + BottlesApplication.getContext().getString(R.string.country_belize) + "', " + R.drawable.belize + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BJ', '" + BottlesApplication.getContext().getString(R.string.country_benin) + "', " + R.drawable.benin + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BT', '" + BottlesApplication.getContext().getString(R.string.country_bhutan) + "', " + R.drawable.bhutan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BO', '" + BottlesApplication.getContext().getString(R.string.country_bolivia) + "', " + R.drawable.bolivia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BA', '" + BottlesApplication.getContext().getString(R.string.country_bosnia) + "', " + R.drawable.bosnia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BW', '" + BottlesApplication.getContext().getString(R.string.country_botswana) + "', " + R.drawable.botswana + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BR', '" + BottlesApplication.getContext().getString(R.string.country_brazil) + "', " + R.drawable.brazil + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VG', '" + BottlesApplication.getContext().getString(R.string.country_british_virgin_islands) + "', " + R.drawable.british_virgin_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BN', '" + BottlesApplication.getContext().getString(R.string.country_brunei) + "', " + R.drawable.brunei + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BG', '" + BottlesApplication.getContext().getString(R.string.country_bulgaria) + "', " + R.drawable.bulgaria + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BF', '" + BottlesApplication.getContext().getString(R.string.country_burkina_faso) + "', " + R.drawable.burkina_faso + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BI', '" + BottlesApplication.getContext().getString(R.string.country_burundi) + "', " + R.drawable.burundi + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KH', '" + BottlesApplication.getContext().getString(R.string.country_cambodia) + "', " + R.drawable.cambodia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CM', '" + BottlesApplication.getContext().getString(R.string.country_cameroon) + "', " + R.drawable.cameroon + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CA', '" + BottlesApplication.getContext().getString(R.string.country_canada) + "', " + R.drawable.canada + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CV', '" + BottlesApplication.getContext().getString(R.string.country_cape_verde) + "', " + R.drawable.cape_verde + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CF', '" + BottlesApplication.getContext().getString(R.string.country_central_african_republic) + "', " + R.drawable.central_african_republic + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TD', '" + BottlesApplication.getContext().getString(R.string.country_chad) + "', " + R.drawable.chad + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CL', '" + BottlesApplication.getContext().getString(R.string.country_chile) + "', " + R.drawable.chile + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CN', '" + BottlesApplication.getContext().getString(R.string.country_china) + "', " + R.drawable.china + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CX', '" + BottlesApplication.getContext().getString(R.string.country_christmas_island) + "', " + R.drawable.christmas_island + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CO', '" + BottlesApplication.getContext().getString(R.string.country_colombia) + "', " + R.drawable.colombia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CD', '" + BottlesApplication.getContext().getString(R.string.country_congo_democratic_republic) + "', " + R.drawable.congo_democratic_republic + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CG', '" + BottlesApplication.getContext().getString(R.string.country_congo_republic) + "', " + R.drawable.congo_republic + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KM', '" + BottlesApplication.getContext().getString(R.string.country_comoros) + "', " + R.drawable.comoros + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CK', '" + BottlesApplication.getContext().getString(R.string.country_cook_islands) + "', " + R.drawable.cook_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CR', '" + BottlesApplication.getContext().getString(R.string.country_costa_rica) + "', " + R.drawable.costa_rica + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CI', '" + BottlesApplication.getContext().getString(R.string.country_cote_divoire) + "', " + R.drawable.cote_divoire + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('HR', '" + BottlesApplication.getContext().getString(R.string.country_croatia) + "', " + R.drawable.croatia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CU', '" + BottlesApplication.getContext().getString(R.string.country_cuba) + "', " + R.drawable.cuba + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CY', '" + BottlesApplication.getContext().getString(R.string.country_cyprus) + "', " + R.drawable.cyprus + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CZ', '" + BottlesApplication.getContext().getString(R.string.country_czech_republic) + "', " + R.drawable.czech_republic + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('DK', '" + BottlesApplication.getContext().getString(R.string.country_denmark) + "', " + R.drawable.denmark + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('DJ', '" + BottlesApplication.getContext().getString(R.string.country_djibouti) + "', " + R.drawable.djibouti + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('DM', '" + BottlesApplication.getContext().getString(R.string.country_dominica) + "', " + R.drawable.dominica + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('DO', '" + BottlesApplication.getContext().getString(R.string.country_dominican_republic) + "', " + R.drawable.dominican_republic + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('EC', '" + BottlesApplication.getContext().getString(R.string.country_ecuador) + "', " + R.drawable.ecuador + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('EG', '" + BottlesApplication.getContext().getString(R.string.country_egypt) + "', " + R.drawable.egypt + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SV', '" + BottlesApplication.getContext().getString(R.string.country_el_salvador) + "', " + R.drawable.el_salvador + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GQ', '" + BottlesApplication.getContext().getString(R.string.country_equatorial_guinea) + "', " + R.drawable.equatorial_guinea + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ER', '" + BottlesApplication.getContext().getString(R.string.country_eritrea) + "', " + R.drawable.eritrea + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('EE', '" + BottlesApplication.getContext().getString(R.string.country_estonia) + "', " + R.drawable.estonia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ET', '" + BottlesApplication.getContext().getString(R.string.country_ethiopia) + "', " + R.drawable.ethiopia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('FK', '" + BottlesApplication.getContext().getString(R.string.country_falkland_islands) + "', " + R.drawable.falkland_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('FO', '" + BottlesApplication.getContext().getString(R.string.country_faroe_islands) + "', " + R.drawable.faroe_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('FJ', '" + BottlesApplication.getContext().getString(R.string.country_fiji) + "', " + R.drawable.fiji + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('FI', '" + BottlesApplication.getContext().getString(R.string.country_finland) + "', " + R.drawable.finland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('FR', '" + BottlesApplication.getContext().getString(R.string.country_france) + "', " + R.drawable.france + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PF', '" + BottlesApplication.getContext().getString(R.string.country_french_polynesia) + "', " + R.drawable.french_polynesia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GA', '" + BottlesApplication.getContext().getString(R.string.country_gabon) + "', " + R.drawable.gabon + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GM', '" + BottlesApplication.getContext().getString(R.string.country_gambia) + "', " + R.drawable.gambia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GE', '" + BottlesApplication.getContext().getString(R.string.country_georgia) + "', " + R.drawable.georgia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('DE', '" + BottlesApplication.getContext().getString(R.string.country_germany) + "', " + R.drawable.germany + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GH', '" + BottlesApplication.getContext().getString(R.string.country_ghana) + "', " + R.drawable.ghana + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GI', '" + BottlesApplication.getContext().getString(R.string.country_gibraltar) + "', " + R.drawable.gibraltar + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GR', '" + BottlesApplication.getContext().getString(R.string.country_greece) + "', " + R.drawable.greece + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GL', '" + BottlesApplication.getContext().getString(R.string.country_greenland) + "', " + R.drawable.greenland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GD', '" + BottlesApplication.getContext().getString(R.string.country_grenada) + "', " + R.drawable.grenada + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GU', '" + BottlesApplication.getContext().getString(R.string.country_guam) + "', " + R.drawable.guam + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GT', '" + BottlesApplication.getContext().getString(R.string.country_guatemala) + "', " + R.drawable.guatemala + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GN', '" + BottlesApplication.getContext().getString(R.string.country_guinea) + "', " + R.drawable.guinea + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GW', '" + BottlesApplication.getContext().getString(R.string.country_guinea_bissau) + "', " + R.drawable.guinea_bissau + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GY', '" + BottlesApplication.getContext().getString(R.string.country_guyana) + "', " + R.drawable.guyana + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('HT', '" + BottlesApplication.getContext().getString(R.string.country_haiti) + "', " + R.drawable.haiti + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('HN', '" + BottlesApplication.getContext().getString(R.string.country_honduras) + "', " + R.drawable.honduras + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('HK', '" + BottlesApplication.getContext().getString(R.string.country_hong_kong) + "', " + R.drawable.hong_kong + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('HU', '" + BottlesApplication.getContext().getString(R.string.country_hungary) + "', " + R.drawable.hungary + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IS', '" + BottlesApplication.getContext().getString(R.string.country_iceland) + "', " + R.drawable.iceland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IN', '" + BottlesApplication.getContext().getString(R.string.country_india) + "', " + R.drawable.india + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ID', '" + BottlesApplication.getContext().getString(R.string.country_indonesia) + "', " + R.drawable.indonesia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IR', '" + BottlesApplication.getContext().getString(R.string.country_iran) + "', " + R.drawable.iran + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IQ', '" + BottlesApplication.getContext().getString(R.string.country_iraq) + "', " + R.drawable.iraq + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IE', '" + BottlesApplication.getContext().getString(R.string.country_ireland) + "', " + R.drawable.ireland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IL', '" + BottlesApplication.getContext().getString(R.string.country_israel) + "', " + R.drawable.israel + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IT', '" + BottlesApplication.getContext().getString(R.string.country_italy) + "', " + R.drawable.italy + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('JM', '" + BottlesApplication.getContext().getString(R.string.country_jamaica) + "', " + R.drawable.jamaica + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('JP', '" + BottlesApplication.getContext().getString(R.string.country_japan) + "', " + R.drawable.japan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('JO', '" + BottlesApplication.getContext().getString(R.string.country_jordan) + "', " + R.drawable.jordan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KZ', '" + BottlesApplication.getContext().getString(R.string.country_kazakhstan) + "', " + R.drawable.kazakhstan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KE', '" + BottlesApplication.getContext().getString(R.string.country_kenya) + "', " + R.drawable.kenya + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KI', '" + BottlesApplication.getContext().getString(R.string.country_kiribati) + "', " + R.drawable.kiribati + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KP', '" + BottlesApplication.getContext().getString(R.string.country_korea_north) + "', " + R.drawable.korea_north + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KR', '" + BottlesApplication.getContext().getString(R.string.country_korea_south) + "', " + R.drawable.korea_south + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KW', '" + BottlesApplication.getContext().getString(R.string.country_kuwait) + "', " + R.drawable.kuwait + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KG', '" + BottlesApplication.getContext().getString(R.string.country_kyrgyzstan) + "', " + R.drawable.kyrgyzstan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LA', '" + BottlesApplication.getContext().getString(R.string.country_laos) + "', " + R.drawable.laos + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LV', '" + BottlesApplication.getContext().getString(R.string.country_latvia) + "', " + R.drawable.latvia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LB', '" + BottlesApplication.getContext().getString(R.string.country_lebanon) + "', " + R.drawable.lebanon + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LS', '" + BottlesApplication.getContext().getString(R.string.country_lesotho) + "', " + R.drawable.lesotho + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LR', '" + BottlesApplication.getContext().getString(R.string.country_liberia) + "', " + R.drawable.liberia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LY', '" + BottlesApplication.getContext().getString(R.string.country_libya) + "', " + R.drawable.libya + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LI', '" + BottlesApplication.getContext().getString(R.string.country_liechtenstein) + "', " + R.drawable.liechtenstein + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LT', '" + BottlesApplication.getContext().getString(R.string.country_lithuania) + "', " + R.drawable.lithuania + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LU', '" + BottlesApplication.getContext().getString(R.string.country_luxembourg) + "', " + R.drawable.luxembourg + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MO', '" + BottlesApplication.getContext().getString(R.string.country_macau) + "', " + R.drawable.macau + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MK', '" + BottlesApplication.getContext().getString(R.string.country_macedonia) + "', " + R.drawable.macedonia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MG', '" + BottlesApplication.getContext().getString(R.string.country_madagascar) + "', " + R.drawable.madagascar + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MW', '" + BottlesApplication.getContext().getString(R.string.country_malawi) + "', " + R.drawable.malawi + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MY', '" + BottlesApplication.getContext().getString(R.string.country_malaysia) + "', " + R.drawable.malaysia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MV', '" + BottlesApplication.getContext().getString(R.string.country_maldives) + "', " + R.drawable.maldives + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ML', '" + BottlesApplication.getContext().getString(R.string.country_mali) + "', " + R.drawable.mali + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MT', '" + BottlesApplication.getContext().getString(R.string.country_malta) + "', " + R.drawable.malta + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MH', '" + BottlesApplication.getContext().getString(R.string.country_marshall_islands) + "', " + R.drawable.marshall_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MQ', '" + BottlesApplication.getContext().getString(R.string.country_martinique) + "', " + R.drawable.martinique + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MR', '" + BottlesApplication.getContext().getString(R.string.country_mauritania) + "', " + R.drawable.mauritania + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MU', '" + BottlesApplication.getContext().getString(R.string.country_mauritius) + "', " + R.drawable.mauritius + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MX', '" + BottlesApplication.getContext().getString(R.string.country_mexico) + "', " + R.drawable.mexico + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('FM', '" + BottlesApplication.getContext().getString(R.string.country_micronesia) + "', " + R.drawable.micronesia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MD', '" + BottlesApplication.getContext().getString(R.string.country_moldova) + "', " + R.drawable.moldova + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MC', '" + BottlesApplication.getContext().getString(R.string.country_monaco) + "', " + R.drawable.monaco + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MN', '" + BottlesApplication.getContext().getString(R.string.country_mongolia) + "', " + R.drawable.mongolia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MS', '" + BottlesApplication.getContext().getString(R.string.country_montserrat) + "', " + R.drawable.montserrat + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ME', '" + BottlesApplication.getContext().getString(R.string.country_montenegro) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MA', '" + BottlesApplication.getContext().getString(R.string.country_morocco) + "', " + R.drawable.morocco + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MZ', '" + BottlesApplication.getContext().getString(R.string.country_mozambique) + "', " + R.drawable.mozambique + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MM', '" + BottlesApplication.getContext().getString(R.string.country_myanmar) + "', " + R.drawable.myanmar + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NA', '" + BottlesApplication.getContext().getString(R.string.country_namibia) + "', " + R.drawable.namibia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NR', '" + BottlesApplication.getContext().getString(R.string.country_nauru) + "', " + R.drawable.nauru + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NP', '" + BottlesApplication.getContext().getString(R.string.country_nepal) + "', " + R.drawable.nepal + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NL', '" + BottlesApplication.getContext().getString(R.string.country_netherlands) + "', " + R.drawable.netherlands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AN', '" + BottlesApplication.getContext().getString(R.string.country_netherlands_antilles) + "', " + R.drawable.netherlands_antilles + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NZ', '" + BottlesApplication.getContext().getString(R.string.country_new_zealand) + "', " + R.drawable.new_zealand + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NI', '" + BottlesApplication.getContext().getString(R.string.country_nicaragua) + "', " + R.drawable.nicaragua + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NE', '" + BottlesApplication.getContext().getString(R.string.country_niger) + "', " + R.drawable.niger + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NG', '" + BottlesApplication.getContext().getString(R.string.country_nigeria) + "', " + R.drawable.nigeria + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NU', '" + BottlesApplication.getContext().getString(R.string.country_niue) + "', " + R.drawable.niue + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NF', '" + BottlesApplication.getContext().getString(R.string.country_norfolk_island) + "', " + R.drawable.norfolk_island + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NO', '" + BottlesApplication.getContext().getString(R.string.country_norway) + "', " + R.drawable.norway + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('OM', '" + BottlesApplication.getContext().getString(R.string.country_oman) + "', " + R.drawable.oman + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PK', '" + BottlesApplication.getContext().getString(R.string.country_pakistan) + "', " + R.drawable.pakistan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PW', '" + BottlesApplication.getContext().getString(R.string.country_palau) + "', " + R.drawable.palau + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PA', '" + BottlesApplication.getContext().getString(R.string.country_panama) + "', " + R.drawable.panama + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PG', '" + BottlesApplication.getContext().getString(R.string.country_papua_new_guinea) + "', " + R.drawable.papua_new_guinea + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PY', '" + BottlesApplication.getContext().getString(R.string.country_paraguay) + "', " + R.drawable.paraguay + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PE', '" + BottlesApplication.getContext().getString(R.string.country_peru) + "', " + R.drawable.peru + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PH', '" + BottlesApplication.getContext().getString(R.string.country_philippines) + "', " + R.drawable.philippines + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PN', '" + BottlesApplication.getContext().getString(R.string.country_pitcairn_islands) + "', " + R.drawable.pitcairn_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PL', '" + BottlesApplication.getContext().getString(R.string.country_poland) + "', " + R.drawable.poland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PT', '" + BottlesApplication.getContext().getString(R.string.country_portugal) + "', " + R.drawable.portugal + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PR', '" + BottlesApplication.getContext().getString(R.string.country_puerto_rico) + "', " + R.drawable.puerto_rico + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('QA', '" + BottlesApplication.getContext().getString(R.string.country_qatar) + "', " + R.drawable.qatar + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('RO', '" + BottlesApplication.getContext().getString(R.string.country_romania) + "', " + R.drawable.romania + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('RU', '" + BottlesApplication.getContext().getString(R.string.country_russian_federation) + "', " + R.drawable.russian_federation + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('RW', '" + BottlesApplication.getContext().getString(R.string.country_rwanda) + "', " + R.drawable.rwanda + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KN', '" + BottlesApplication.getContext().getString(R.string.country_saint_kitts_and_nevis) + "', " + R.drawable.saint_kitts_and_nevis + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LC', '" + BottlesApplication.getContext().getString(R.string.country_saint_lucia) + "', " + R.drawable.saint_lucia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VC', '" + BottlesApplication.getContext().getString(R.string.country_saint_vicent_and_the_grenadines) + "', " + R.drawable.saint_vicent_and_the_grenadines + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('WS', '" + BottlesApplication.getContext().getString(R.string.country_samoa) + "', " + R.drawable.samoa + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SM', '" + BottlesApplication.getContext().getString(R.string.country_san_marino) + "', " + R.drawable.san_marino + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ST', '" + BottlesApplication.getContext().getString(R.string.country_sao_tome_and_principe) + "', " + R.drawable.sao_tome_and_principe + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SA', '" + BottlesApplication.getContext().getString(R.string.country_saudi_arabia) + "', " + R.drawable.saudi_arabia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SN', '" + BottlesApplication.getContext().getString(R.string.country_senegal) + "', " + R.drawable.senegal + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('RS', '" + BottlesApplication.getContext().getString(R.string.country_serbia) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SC', '" + BottlesApplication.getContext().getString(R.string.country_seychelles) + "', " + R.drawable.seychelles + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SL', '" + BottlesApplication.getContext().getString(R.string.country_sierra_leone) + "', " + R.drawable.sierra_leone + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SG', '" + BottlesApplication.getContext().getString(R.string.country_singapore) + "', " + R.drawable.singapore + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SK', '" + BottlesApplication.getContext().getString(R.string.country_slovakia) + "', " + R.drawable.slovakia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SI', '" + BottlesApplication.getContext().getString(R.string.country_slovenia) + "', " + R.drawable.slovenia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SB', '" + BottlesApplication.getContext().getString(R.string.country_solomon_islands) + "', " + R.drawable.solomon_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SO', '" + BottlesApplication.getContext().getString(R.string.country_somalia) + "', " + R.drawable.somalia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ZA', '" + BottlesApplication.getContext().getString(R.string.country_south_africa) + "', " + R.drawable.south_africa + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ES', '" + BottlesApplication.getContext().getString(R.string.country_spain) + "', " + R.drawable.spain + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('LK', '" + BottlesApplication.getContext().getString(R.string.country_sri_lanka) + "', " + R.drawable.sri_lanka + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SD', '" + BottlesApplication.getContext().getString(R.string.country_sudan) + "', " + R.drawable.sudan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SR', '" + BottlesApplication.getContext().getString(R.string.country_suriname) + "', " + R.drawable.suriname + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SZ', '" + BottlesApplication.getContext().getString(R.string.country_swaziland) + "', " + R.drawable.swaziland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SE', '" + BottlesApplication.getContext().getString(R.string.country_sweden) + "', " + R.drawable.sweden + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CH', '" + BottlesApplication.getContext().getString(R.string.country_switzerland) + "', " + R.drawable.switzerland + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SY', '" + BottlesApplication.getContext().getString(R.string.country_syria) + "', " + R.drawable.syria + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TW', '" + BottlesApplication.getContext().getString(R.string.country_taiwan) + "', " + R.drawable.taiwan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TJ', '" + BottlesApplication.getContext().getString(R.string.country_tajikistan) + "', " + R.drawable.tajikistan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TZ', '" + BottlesApplication.getContext().getString(R.string.country_tanzania) + "', " + R.drawable.tanzania + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TH', '" + BottlesApplication.getContext().getString(R.string.country_thailand) + "', " + R.drawable.thailand + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TL', '" + BottlesApplication.getContext().getString(R.string.country_timor_leste) + "', " + R.drawable.timor_leste + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TG', '" + BottlesApplication.getContext().getString(R.string.country_togo) + "', " + R.drawable.togo + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TO', '" + BottlesApplication.getContext().getString(R.string.country_tonga) + "', " + R.drawable.tonga + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TT', '" + BottlesApplication.getContext().getString(R.string.country_trinidad_and_tobago) + "', " + R.drawable.trinidad_and_tobago + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TN', '" + BottlesApplication.getContext().getString(R.string.country_tunisia) + "', " + R.drawable.tunisia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TR', '" + BottlesApplication.getContext().getString(R.string.country_turkey) + "', " + R.drawable.turkey + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TM', '" + BottlesApplication.getContext().getString(R.string.country_turkmenistan) + "', " + R.drawable.turkmenistan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TC', '" + BottlesApplication.getContext().getString(R.string.country_turks_and_caicos_islands) + "', " + R.drawable.turks_and_caicos_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TV', '" + BottlesApplication.getContext().getString(R.string.country_tuvalu) + "', " + R.drawable.tuvalu + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('UG', '" + BottlesApplication.getContext().getString(R.string.country_uganda) + "', " + R.drawable.uganda + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('UA', '" + BottlesApplication.getContext().getString(R.string.country_ukraine) + "', " + R.drawable.ukraine + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AE', '" + BottlesApplication.getContext().getString(R.string.country_united_arab_emirates) + "', " + R.drawable.united_arab_emirates + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GB', '" + BottlesApplication.getContext().getString(R.string.country_united_kingdom) + "', " + R.drawable.united_kingdom + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('US', '" + BottlesApplication.getContext().getString(R.string.country_united_states_of_america) + "', " + R.drawable.united_states_of_america + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('UY', '" + BottlesApplication.getContext().getString(R.string.country_uruguay) + "', " + R.drawable.uruguay + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VI', '" + BottlesApplication.getContext().getString(R.string.country_us_virgin_islands) + "', " + R.drawable.us_virgin_islands + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('UZ', '" + BottlesApplication.getContext().getString(R.string.country_uzbekistan) + "', " + R.drawable.uzbekistan + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VU', '" + BottlesApplication.getContext().getString(R.string.country_vanuatu) + "', " + R.drawable.vanuatu + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VA', '" + BottlesApplication.getContext().getString(R.string.country_vatican_city) + "', " + R.drawable.vatican_city + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VE', '" + BottlesApplication.getContext().getString(R.string.country_venezuela) + "', " + R.drawable.venezuela + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('VN', '" + BottlesApplication.getContext().getString(R.string.country_vietnam) + "', " + R.drawable.vietnam + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('YE', '" + BottlesApplication.getContext().getString(R.string.country_yemen) + "', " + R.drawable.yemen + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ZM', '" + BottlesApplication.getContext().getString(R.string.country_zambia) + "', " + R.drawable.zambia + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('ZW', '" + BottlesApplication.getContext().getString(R.string.country_zimbabwe) + "', " + R.drawable.zimbabwe + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('CC', '" + BottlesApplication.getContext().getString(R.string.country_cocos_keeling_islands) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('HM', '" + BottlesApplication.getContext().getString(R.string.country_heard_island_and_mcdonald_islands) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('NC', '" + BottlesApplication.getContext().getString(R.string.country_new_caledonia) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('YT', '" + BottlesApplication.getContext().getString(R.string.country_mayotte) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('PM', '" + BottlesApplication.getContext().getString(R.string.country_saint_pierre_and_miquelon) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('WF', '" + BottlesApplication.getContext().getString(R.string.country_wallis_and_futuna) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TF', '" + BottlesApplication.getContext().getString(R.string.country_french_southern_and_antarctic_lands) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BV', '" + BottlesApplication.getContext().getString(R.string.country_bouvet_island) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TK', '" + BottlesApplication.getContext().getString(R.string.country_tokelau) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GG', '" + BottlesApplication.getContext().getString(R.string.country_guernsey) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IM', '" + BottlesApplication.getContext().getString(R.string.country_isle_of_man) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('JE', '" + BottlesApplication.getContext().getString(R.string.country_jersey) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AI', '" + BottlesApplication.getContext().getString(R.string.country_anguilla) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('BM', '" + BottlesApplication.getContext().getString(R.string.country_bermuda) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('IO', '" + BottlesApplication.getContext().getString(R.string.country_british_indian_ocean_territory) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('KY', '" + BottlesApplication.getContext().getString(R.string.country_cayman_islands) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SH', '" + BottlesApplication.getContext().getString(R.string.country_saint_helena) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GS', '" + BottlesApplication.getContext().getString(R.string.country_south_georgia__south_sandwich_islands) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('MP', '" + BottlesApplication.getContext().getString(R.string.country_northern_mariana_islands) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AS', '" + BottlesApplication.getContext().getString(R.string.country_american_samoa) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('UM', '" + BottlesApplication.getContext().getString(R.string.country_baker_island) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GF', '" + BottlesApplication.getContext().getString(R.string.country_french_guiana) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('GP', '" + BottlesApplication.getContext().getString(R.string.country_guadeloupe) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('RE', '" + BottlesApplication.getContext().getString(R.string.country_reunion) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AX', '" + BottlesApplication.getContext().getString(R.string.country_aland) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AW', '" + BottlesApplication.getContext().getString(R.string.country_aruba) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('SJ', '" + BottlesApplication.getContext().getString(R.string.country_svalbard) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('AC', '" + BottlesApplication.getContext().getString(R.string.country_ascension) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values ('TA', '" + BottlesApplication.getContext().getString(R.string.country_tristan_da_cunha) + "', " + R.drawable.no_flag + ")");
    }
}
