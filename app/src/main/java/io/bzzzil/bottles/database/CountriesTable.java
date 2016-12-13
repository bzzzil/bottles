package io.bzzzil.bottles.database;

import android.content.Context;
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

    public static void onCreate(SQLiteDatabase db, Context context) {
        db.execSQL("create table " + TABLE_COUNTRIES + " ("
                + COLUMN_ID + " varchar(2) primary key, "
                + COLUMN_NAME + " text not null, "
                + COLUMN_FLAG_RESOURCE_ID + " integer not null "
                + ")");
        insertCountries(db, context);
    }

    public static void onUpgrade(SQLiteDatabase db, Context context, int oldVersion, int newVersion) {
        Log.w(CountriesTable.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion
                        + ". ALL DATA WILL BE DESTROYED");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRIES);
        onCreate(db, context);
    }

    private static void insertCountries(SQLiteDatabase db, Context context)
    {
        //db.beginTransaction();
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CI', '" + context.getString(R.string.country_cote_divoire) + "', " + R.drawable.flag_ci + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AF', '" + context.getString(R.string.country_afghanistan) + "', " + R.drawable.flag_af + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AL', '" + context.getString(R.string.country_albania) + "', " + R.drawable.flag_al + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('DZ', '" + context.getString(R.string.country_algeria) + "', " + R.drawable.flag_dz + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AD', '" + context.getString(R.string.country_andorra) + "', " + R.drawable.flag_ad + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AO', '" + context.getString(R.string.country_angola) + "', " + R.drawable.flag_ao + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AG', '" + context.getString(R.string.country_antigua_and_barbuda) + "', " + R.drawable.flag_ag+ ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AR', '" + context.getString(R.string.country_argentina) + "', " + R.drawable.flag_ar + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AM', '" + context.getString(R.string.country_armenia) + "', " + R.drawable.flag_am + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AU', '" + context.getString(R.string.country_australia) + "', " + R.drawable.flag_au + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AT', '" + context.getString(R.string.country_austria) + "', " + R.drawable.flag_at + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AZ', '" + context.getString(R.string.country_azerbaijan) + "', " + R.drawable.flag_az + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BS', '" + context.getString(R.string.country_bahamas) + "', " + R.drawable.flag_bs + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BH', '" + context.getString(R.string.country_bahrain) + "', " + R.drawable.flag_bh + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BD', '" + context.getString(R.string.country_bangladesh) + "', " + R.drawable.flag_bd + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BB', '" + context.getString(R.string.country_barbados) + "', " + R.drawable.flag_bb + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BY', '" + context.getString(R.string.country_belarus) + "', " + R.drawable.flag_by + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BE', '" + context.getString(R.string.country_belgium) + "', " + R.drawable.flag_be + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BZ', '" + context.getString(R.string.country_belize) + "', " + R.drawable.flag_bz + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BJ', '" + context.getString(R.string.country_benin) + "', " + R.drawable.flag_bj + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BT', '" + context.getString(R.string.country_bhutan) + "', " + R.drawable.flag_bt + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BO', '" + context.getString(R.string.country_bolivia) + "', " + R.drawable.flag_bo + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BA', '" + context.getString(R.string.country_bosnia) + "', " + R.drawable.flag_ba + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BW', '" + context.getString(R.string.country_botswana) + "', " + R.drawable.flag_bw + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BR', '" + context.getString(R.string.country_brazil) + "', " + R.drawable.flag_br + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('VG', '" + context.getString(R.string.country_british_virgin_islands) + "', " + R.drawable.flag_vg + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BN', '" + context.getString(R.string.country_brunei) + "', " + R.drawable.flag_bn + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BG', '" + context.getString(R.string.country_bulgaria) + "', " + R.drawable.flag_bg + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BF', '" + context.getString(R.string.country_burkina_faso) + "', " + R.drawable.flag_bf + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BI', '" + context.getString(R.string.country_burundi) + "', " + R.drawable.flag_bi + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('KH', '" + context.getString(R.string.country_cambodia) + "', " + R.drawable.flag_kh + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CM', '" + context.getString(R.string.country_cameroon) + "', " + R.drawable.flag_cm + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CA', '" + context.getString(R.string.country_canada) + "', " + R.drawable.flag_ca + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CV', '" + context.getString(R.string.country_cape_verde) + "', " + R.drawable.flag_cv + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CF', '" + context.getString(R.string.country_central_african_republic) + "', " + R.drawable.flag_cf + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TD', '" + context.getString(R.string.country_chad) + "', " + R.drawable.flag_td + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CL', '" + context.getString(R.string.country_chile) + "', " + R.drawable.flag_cl + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CN', '" + context.getString(R.string.country_china) + "', " + R.drawable.flag_cn + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CX', '" + context.getString(R.string.country_christmas_island) + "', " + R.drawable.flag_cx + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CO', '" + context.getString(R.string.country_colombia) + "', " + R.drawable.flag_co + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CD', '" + context.getString(R.string.country_congo_democratic_republic) + "', " + R.drawable.flag_cd + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CG', '" + context.getString(R.string.country_congo_republic) + "', " + R.drawable.flag_cg + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('KM', '" + context.getString(R.string.country_comoros) + "', " + R.drawable.flag_km + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CK', '" + context.getString(R.string.country_cook_islands) + "', " + R.drawable.flag_ck + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CR', '" + context.getString(R.string.country_costa_rica) + "', " + R.drawable.flag_cr + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('HR', '" + context.getString(R.string.country_croatia) + "', " + R.drawable.flag_hr + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CU', '" + context.getString(R.string.country_cuba) + "', " + R.drawable.flag_cu + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CY', '" + context.getString(R.string.country_cyprus) + "', " + R.drawable.flag_cy + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CZ', '" + context.getString(R.string.country_czech_republic) + "', " + R.drawable.flag_cz + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('DK', '" + context.getString(R.string.country_denmark) + "', " + R.drawable.flag_dk + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('DJ', '" + context.getString(R.string.country_djibouti) + "', " + R.drawable.flag_dj + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('DM', '" + context.getString(R.string.country_dominica) + "', " + R.drawable.flag_dm + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('DO', '" + context.getString(R.string.country_dominican_republic) + "', " + R.drawable.flag_do + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('EC', '" + context.getString(R.string.country_ecuador) + "', " + R.drawable.flag_ec + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('EG', '" + context.getString(R.string.country_egypt) + "', " + R.drawable.flag_eg + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SV', '" + context.getString(R.string.country_el_salvador) + "', " + R.drawable.flag_sv + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GQ', '" + context.getString(R.string.country_equatorial_guinea) + "', " + R.drawable.flag_gq + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('ER', '" + context.getString(R.string.country_eritrea) + "', " + R.drawable.flag_er + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('EE', '" + context.getString(R.string.country_estonia) + "', " + R.drawable.flag_ee + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('ET', '" + context.getString(R.string.country_ethiopia) + "', " + R.drawable.flag_et + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('FK', '" + context.getString(R.string.country_falkland_islands) + "', " + R.drawable.flag_fk + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('FO', '" + context.getString(R.string.country_faroe_islands) + "', " + R.drawable.flag_fo + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('FJ', '" + context.getString(R.string.country_fiji) + "', " + R.drawable.flag_fj + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('FI', '" + context.getString(R.string.country_finland) + "', " + R.drawable.flag_fi + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('FR', '" + context.getString(R.string.country_france) + "', " + R.drawable.flag_fr + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PF', '" + context.getString(R.string.country_french_polynesia) + "', " + R.drawable.flag_pf + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GA', '" + context.getString(R.string.country_gabon) + "', " + R.drawable.flag_ga + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GM', '" + context.getString(R.string.country_gambia) + "', " + R.drawable.flag_gm + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GE', '" + context.getString(R.string.country_georgia) + "', " + R.drawable.flag_ge + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('DE', '" + context.getString(R.string.country_germany) + "', " + R.drawable.flag_de + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GH', '" + context.getString(R.string.country_ghana) + "', " + R.drawable.flag_gh + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GI', '" + context.getString(R.string.country_gibraltar) + "', " + R.drawable.flag_gi + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GR', '" + context.getString(R.string.country_greece) + "', " + R.drawable.flag_gr + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GL', '" + context.getString(R.string.country_greenland) + "', " + R.drawable.flag_gl + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GD', '" + context.getString(R.string.country_grenada) + "', " + R.drawable.flag_gd + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GU', '" + context.getString(R.string.country_guam) + "', " + R.drawable.flag_gu + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GT', '" + context.getString(R.string.country_guatemala) + "', " + R.drawable.flag_gt + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GN', '" + context.getString(R.string.country_guinea) + "', " + R.drawable.flag_gn + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GW', '" + context.getString(R.string.country_guinea_bissau) + "', " + R.drawable.flag_gw + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GY', '" + context.getString(R.string.country_guyana) + "', " + R.drawable.flag_gy + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('HT', '" + context.getString(R.string.country_haiti) + "', " + R.drawable.flag_ht + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('HN', '" + context.getString(R.string.country_honduras) + "', " + R.drawable.flag_hn + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('HK', '" + context.getString(R.string.country_hong_kong) + "', " + R.drawable.flag_hk + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('HU', '" + context.getString(R.string.country_hungary) + "', " + R.drawable.flag_hu + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('IS', '" + context.getString(R.string.country_iceland) + "', " + R.drawable.flag_is + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('IN', '" + context.getString(R.string.country_india) + "', " + R.drawable.flag_in + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('ID', '" + context.getString(R.string.country_indonesia) + "', " + R.drawable.flag_id + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('IR', '" + context.getString(R.string.country_iran) + "', " + R.drawable.flag_ir + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('IQ', '" + context.getString(R.string.country_iraq) + "', " + R.drawable.flag_iq + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('IE', '" + context.getString(R.string.country_ireland) + "', " + R.drawable.flag_ie + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('IL', '" + context.getString(R.string.country_israel) + "', " + R.drawable.flag_il + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('IT', '" + context.getString(R.string.country_italy) + "', " + R.drawable.flag_it + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('JM', '" + context.getString(R.string.country_jamaica) + "', " + R.drawable.flag_jm + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('JP', '" + context.getString(R.string.country_japan) + "', " + R.drawable.flag_jp + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('JO', '" + context.getString(R.string.country_jordan) + "', " + R.drawable.flag_jo + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('KZ', '" + context.getString(R.string.country_kazakhstan) + "', " + R.drawable.flag_kz + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('KE', '" + context.getString(R.string.country_kenya) + "', " + R.drawable.flag_ke + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('KI', '" + context.getString(R.string.country_kiribati) + "', " + R.drawable.flag_ki + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('KP', '" + context.getString(R.string.country_korea_north) + "', " + R.drawable.flag_kp + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('KR', '" + context.getString(R.string.country_korea_south) + "', " + R.drawable.flag_kr + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('KW', '" + context.getString(R.string.country_kuwait) + "', " + R.drawable.flag_kw + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('KG', '" + context.getString(R.string.country_kyrgyzstan) + "', " + R.drawable.flag_kg + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('LA', '" + context.getString(R.string.country_laos) + "', " + R.drawable.flag_la + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('LV', '" + context.getString(R.string.country_latvia) + "', " + R.drawable.flag_lv + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('LB', '" + context.getString(R.string.country_lebanon) + "', " + R.drawable.flag_lb + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('LS', '" + context.getString(R.string.country_lesotho) + "', " + R.drawable.flag_ls + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('LR', '" + context.getString(R.string.country_liberia) + "', " + R.drawable.flag_lr + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('LY', '" + context.getString(R.string.country_libya) + "', " + R.drawable.flag_ly + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('LI', '" + context.getString(R.string.country_liechtenstein) + "', " + R.drawable.flag_li + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('LT', '" + context.getString(R.string.country_lithuania) + "', " + R.drawable.flag_lt + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('LU', '" + context.getString(R.string.country_luxembourg) + "', " + R.drawable.flag_lu + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MO', '" + context.getString(R.string.country_macau) + "', " + R.drawable.flag_mo + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MK', '" + context.getString(R.string.country_macedonia) + "', " + R.drawable.flag_mk + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MG', '" + context.getString(R.string.country_madagascar) + "', " + R.drawable.flag_mg + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MW', '" + context.getString(R.string.country_malawi) + "', " + R.drawable.flag_mw + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MY', '" + context.getString(R.string.country_malaysia) + "', " + R.drawable.flag_my + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MV', '" + context.getString(R.string.country_maldives) + "', " + R.drawable.flag_mv + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('ML', '" + context.getString(R.string.country_mali) + "', " + R.drawable.flag_ml + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MT', '" + context.getString(R.string.country_malta) + "', " + R.drawable.flag_mt + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MH', '" + context.getString(R.string.country_marshall_islands) + "', " + R.drawable.flag_mh + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MQ', '" + context.getString(R.string.country_martinique) + "', " + R.drawable.flag_mq + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MR', '" + context.getString(R.string.country_mauritania) + "', " + R.drawable.flag_mr + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MU', '" + context.getString(R.string.country_mauritius) + "', " + R.drawable.flag_mu + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MX', '" + context.getString(R.string.country_mexico) + "', " + R.drawable.flag_mx + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('FM', '" + context.getString(R.string.country_micronesia) + "', " + R.drawable.flag_fm + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MD', '" + context.getString(R.string.country_moldova) + "', " + R.drawable.flag_md + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MC', '" + context.getString(R.string.country_monaco) + "', " + R.drawable.flag_mc + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MN', '" + context.getString(R.string.country_mongolia) + "', " + R.drawable.flag_mn + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MS', '" + context.getString(R.string.country_montserrat) + "', " + R.drawable.flag_ms + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('ME', '" + context.getString(R.string.country_montenegro) + "', " + R.drawable.flag_me + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MA', '" + context.getString(R.string.country_morocco) + "', " + R.drawable.flag_ma + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MZ', '" + context.getString(R.string.country_mozambique) + "', " + R.drawable.flag_mz + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MM', '" + context.getString(R.string.country_myanmar) + "', " + R.drawable.flag_mm + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('NA', '" + context.getString(R.string.country_namibia) + "', " + R.drawable.flag_na + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('NR', '" + context.getString(R.string.country_nauru) + "', " + R.drawable.flag_nr + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('NP', '" + context.getString(R.string.country_nepal) + "', " + R.drawable.flag_np + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('NL', '" + context.getString(R.string.country_netherlands) + "', " + R.drawable.flag_nl + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AN', '" + context.getString(R.string.country_netherlands_antilles) + "', " + R.drawable.flag_an + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('NZ', '" + context.getString(R.string.country_new_zealand) + "', " + R.drawable.flag_nz + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('NI', '" + context.getString(R.string.country_nicaragua) + "', " + R.drawable.flag_ni + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('NE', '" + context.getString(R.string.country_niger) + "', " + R.drawable.flag_ne + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('NG', '" + context.getString(R.string.country_nigeria) + "', " + R.drawable.flag_ng + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('NU', '" + context.getString(R.string.country_niue) + "', " + R.drawable.flag_nu + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('NF', '" + context.getString(R.string.country_norfolk_island) + "', " + R.drawable.flag_nf + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('NO', '" + context.getString(R.string.country_norway) + "', " + R.drawable.flag_no + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('OM', '" + context.getString(R.string.country_oman) + "', " + R.drawable.flag_om + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PK', '" + context.getString(R.string.country_pakistan) + "', " + R.drawable.flag_pk + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PW', '" + context.getString(R.string.country_palau) + "', " + R.drawable.flag_pw + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PA', '" + context.getString(R.string.country_panama) + "', " + R.drawable.flag_pa + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PG', '" + context.getString(R.string.country_papua_new_guinea) + "', " + R.drawable.flag_pg + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PY', '" + context.getString(R.string.country_paraguay) + "', " + R.drawable.flag_py + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PE', '" + context.getString(R.string.country_peru) + "', " + R.drawable.flag_pe + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PH', '" + context.getString(R.string.country_philippines) + "', " + R.drawable.flag_ph + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PN', '" + context.getString(R.string.country_pitcairn_islands) + "', " + R.drawable.flag_pn + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PL', '" + context.getString(R.string.country_poland) + "', " + R.drawable.flag_pl + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PT', '" + context.getString(R.string.country_portugal) + "', " + R.drawable.flag_pt + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PR', '" + context.getString(R.string.country_puerto_rico) + "', " + R.drawable.flag_pr + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('QA', '" + context.getString(R.string.country_qatar) + "', " + R.drawable.flag_qa + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('RO', '" + context.getString(R.string.country_romania) + "', " + R.drawable.flag_ro + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('RU', '" + context.getString(R.string.country_russian_federation) + "', " + R.drawable.flag_ru + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('RW', '" + context.getString(R.string.country_rwanda) + "', " + R.drawable.flag_rw + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('KN', '" + context.getString(R.string.country_saint_kitts_and_nevis) + "', " + R.drawable.flag_kn + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('LC', '" + context.getString(R.string.country_saint_lucia) + "', " + R.drawable.flag_lc + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('VC', '" + context.getString(R.string.country_saint_vicent_and_the_grenadines) + "', " + R.drawable.flag_vc + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('WS', '" + context.getString(R.string.country_samoa) + "', " + R.drawable.flag_ws + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SM', '" + context.getString(R.string.country_san_marino) + "', " + R.drawable.flag_sm + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('ST', '" + context.getString(R.string.country_sao_tome_and_principe) + "', " + R.drawable.flag_st + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SA', '" + context.getString(R.string.country_saudi_arabia) + "', " + R.drawable.flag_sa + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SN', '" + context.getString(R.string.country_senegal) + "', " + R.drawable.flag_sn + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('RS', '" + context.getString(R.string.country_serbia) + "', " + R.drawable.flag_rs + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SC', '" + context.getString(R.string.country_seychelles) + "', " + R.drawable.flag_sc + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SL', '" + context.getString(R.string.country_sierra_leone) + "', " + R.drawable.flag_sl + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SG', '" + context.getString(R.string.country_singapore) + "', " + R.drawable.flag_sg + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SK', '" + context.getString(R.string.country_slovakia) + "', " + R.drawable.flag_sk + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SI', '" + context.getString(R.string.country_slovenia) + "', " + R.drawable.flag_si + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SB', '" + context.getString(R.string.country_solomon_islands) + "', " + R.drawable.flag_sb + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SO', '" + context.getString(R.string.country_somalia) + "', " + R.drawable.flag_so + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('ZA', '" + context.getString(R.string.country_south_africa) + "', " + R.drawable.flag_za + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('ES', '" + context.getString(R.string.country_spain) + "', " + R.drawable.flag_es + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('LK', '" + context.getString(R.string.country_sri_lanka) + "', " + R.drawable.flag_lk + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SD', '" + context.getString(R.string.country_sudan) + "', " + R.drawable.flag_sd + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SR', '" + context.getString(R.string.country_suriname) + "', " + R.drawable.flag_sr + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SZ', '" + context.getString(R.string.country_swaziland) + "', " + R.drawable.flag_sz + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SE', '" + context.getString(R.string.country_sweden) + "', " + R.drawable.flag_se + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CH', '" + context.getString(R.string.country_switzerland) + "', " + R.drawable.flag_ch + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SY', '" + context.getString(R.string.country_syria) + "', " + R.drawable.flag_sy + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TW', '" + context.getString(R.string.country_taiwan) + "', " + R.drawable.flag_tw + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TJ', '" + context.getString(R.string.country_tajikistan) + "', " + R.drawable.flag_tj + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TZ', '" + context.getString(R.string.country_tanzania) + "', " + R.drawable.flag_tz + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TH', '" + context.getString(R.string.country_thailand) + "', " + R.drawable.flag_th + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TL', '" + context.getString(R.string.country_timor_leste) + "', " + R.drawable.flag_tl + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TG', '" + context.getString(R.string.country_togo) + "', " + R.drawable.flag_tg + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TO', '" + context.getString(R.string.country_tonga) + "', " + R.drawable.flag_to + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TT', '" + context.getString(R.string.country_trinidad_and_tobago) + "', " + R.drawable.flag_tt + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TN', '" + context.getString(R.string.country_tunisia) + "', " + R.drawable.flag_tn + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TR', '" + context.getString(R.string.country_turkey) + "', " + R.drawable.flag_tr + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TM', '" + context.getString(R.string.country_turkmenistan) + "', " + R.drawable.flag_tm + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TC', '" + context.getString(R.string.country_turks_and_caicos_islands) + "', " + R.drawable.flag_tc + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TV', '" + context.getString(R.string.country_tuvalu) + "', " + R.drawable.flag_tv + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('UG', '" + context.getString(R.string.country_uganda) + "', " + R.drawable.flag_ug + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('UA', '" + context.getString(R.string.country_ukraine) + "', " + R.drawable.flag_ua + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AE', '" + context.getString(R.string.country_united_arab_emirates) + "', " + R.drawable.flag_ae + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GB', '" + context.getString(R.string.country_united_kingdom) + "', " + R.drawable.flag_gb + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('US', '" + context.getString(R.string.country_united_states_of_america) + "', " + R.drawable.flag_us + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('UY', '" + context.getString(R.string.country_uruguay) + "', " + R.drawable.flag_uy + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('VI', '" + context.getString(R.string.country_us_virgin_islands) + "', " + R.drawable.flag_vi + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('UZ', '" + context.getString(R.string.country_uzbekistan) + "', " + R.drawable.flag_uz + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('VU', '" + context.getString(R.string.country_vanuatu) + "', " + R.drawable.flag_vu + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('VA', '" + context.getString(R.string.country_vatican_city) + "', " + R.drawable.flag_va + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('VE', '" + context.getString(R.string.country_venezuela) + "', " + R.drawable.flag_ve + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('VN', '" + context.getString(R.string.country_vietnam) + "', " + R.drawable.flag_vn + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('YE', '" + context.getString(R.string.country_yemen) + "', " + R.drawable.flag_ye + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('ZM', '" + context.getString(R.string.country_zambia) + "', " + R.drawable.flag_zm + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('ZW', '" + context.getString(R.string.country_zimbabwe) + "', " + R.drawable.flag_zw + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CC', '" + context.getString(R.string.country_cocos_keeling_islands) + "', " + R.drawable.flag_cc + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('HM', '" + context.getString(R.string.country_heard_island_and_mcdonald_islands) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('NC', '" + context.getString(R.string.country_new_caledonia) + "', " + R.drawable.flag_nc + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('YT', '" + context.getString(R.string.country_mayotte) + "', " + R.drawable.flag_yt + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PM', '" + context.getString(R.string.country_saint_pierre_and_miquelon) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('WF', '" + context.getString(R.string.country_wallis_and_futuna) + "', " + R.drawable.flag_wf + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TF', '" + context.getString(R.string.country_french_southern_and_antarctic_lands) + "', " + R.drawable.flag_tf + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BV', '" + context.getString(R.string.country_bouvet_island) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TK', '" + context.getString(R.string.country_tokelau) + "', " + R.drawable.flag_tk + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GG', '" + context.getString(R.string.country_guernsey) + "', " + R.drawable.flag_gg + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('IM', '" + context.getString(R.string.country_isle_of_man) + "', " + R.drawable.flag_im + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('JE', '" + context.getString(R.string.country_jersey) + "', " + R.drawable.flag_je + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AI', '" + context.getString(R.string.country_anguilla) + "', " + R.drawable.flag_ai + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BM', '" + context.getString(R.string.country_bermuda) + "', " + R.drawable.flag_bm + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('IO', '" + context.getString(R.string.country_british_indian_ocean_territory) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('KY', '" + context.getString(R.string.country_cayman_islands) + "', " + R.drawable.flag_ky + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SH', '" + context.getString(R.string.country_saint_helena) + "', " + R.drawable.flag_sh + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GS', '" + context.getString(R.string.country_south_georgia__south_sandwich_islands) + "', " + R.drawable.flag_gs + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MP', '" + context.getString(R.string.country_northern_mariana_islands) + "', " + R.drawable.flag_mp + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AS', '" + context.getString(R.string.country_american_samoa) + "', " + R.drawable.flag_as + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('UM', '" + context.getString(R.string.country_baker_island) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GF', '" + context.getString(R.string.country_french_guiana) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('GP', '" + context.getString(R.string.country_guadeloupe) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('RE', '" + context.getString(R.string.country_reunion) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AX', '" + context.getString(R.string.country_aland) + "', " + R.drawable.flag_ax + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AW', '" + context.getString(R.string.country_aruba) + "', " + R.drawable.flag_aw + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SJ', '" + context.getString(R.string.country_svalbard) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AC', '" + context.getString(R.string.country_ascension) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('TA', '" + context.getString(R.string.country_tristan_da_cunha) + "', " + R.drawable.no_flag + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('EH', '" + context.getString(R.string.country_western_sahara) + "', " + R.drawable.flag_eh + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('CW', '" + context.getString(R.string.country_curacao) + "', " + R.drawable.flag_cw + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('BL', '" + context.getString(R.string.country_saint_barthelemy) + "', " + R.drawable.flag_bl + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('AQ', '" + context.getString(R.string.country_antarctica) + "', " + R.drawable.flag_aq + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('SS', '" + context.getString(R.string.country_south_sudan) + "', " + R.drawable.flag_ss + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('MF', '" + context.getString(R.string.country_saint_martin) + "', " + R.drawable.flag_mf + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('PS', '" + context.getString(R.string.country_palestina) + "', " + R.drawable.flag_ps + ")");
        db.execSQL("insert into " + CountriesTable.TABLE_COUNTRIES + " values('EU', '" + context.getString(R.string.country_european_union) + "', " + R.drawable.flag_eu + ")");
        //db.endTransaction();
    }
}
