package untshare.progsby.com.untshare;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import FragmentView.ListUpload;
import adapter.ExpandListAdapter;
import session.SessionManager;

import static android.view.Gravity.START;

public class Main2Activity extends AppCompatActivity {

    private ArrayList listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private ExpandableListView expListView;
    private ExpandListAdapter listAdapter;
    private String internal="Berita Internal";
    private String external="Berita External";
    private String jurusan="Berita Jurusan";
    private String fakultas="Berita Fakultas";
    private String politik="Politik";
    private String hukum="Hukum";
    private String OR ="Olah Raga";
    private String spkBl="Sepak Bola";
    private String food ="Food";
    private String kesehatan="Kesehatan";
    private String lifestyle="Lifestyle";
    private String otomotif="Otomotif";
    private String traveling="Traveling";
    private String fotografi="Fotografi";
    private String teknologi="Teknologi";
    private String finance="Finance";
    private DrawerLayout drawer;
    private SessionManager session;
    private static Object sid="idUser";
    private  static Object simage ="image";
    private static Object snama ="username";
    private static Object statusUser ="status";
    private static Object sNIK ="nik";
    private static Object sNamaLengkap ="nama";
    private static Object sTelp ="telp";
    private static Object sEmail ="email";
    private static Object sAlamat ="alamat";
    private static Object sJurusan ="jurusan";
    private static Object sNamaJurusan ="namaJurusan";
    private static Object sFakultas ="fakultas";
    private static Object sNamaFakultas ="namaFakultas";
    private HashMap<String, Object>user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        session =new SessionManager(getApplicationContext());
        session.checkLogin();
        try {
            user = session.getUserDetails();
            sid		= user.get(SessionManager.sid);
            snama	= user.get(SessionManager.sUserName);
            simage	= user.get(SessionManager.simage);
            statusUser = user.get(SessionManager.statusAdmin);
            sNIK = user.get(SessionManager.sNIK);
            sNamaLengkap = user.get(SessionManager.sNamaLengkap);
            sTelp = user.get(SessionManager.sTelp);
            sEmail = user.get(SessionManager.sEmail);
            sAlamat = user.get(SessionManager.sAlamat);
            sJurusan = user.get(SessionManager.sJurusan);
            sNamaJurusan = user.get(SessionManager.sNamaJurusan);
            sFakultas = user.get(SessionManager.sFakultas);
            sNamaFakultas = user.get(SessionManager.sNamaFakultas);
        } catch (Exception e) {
            // TODO: handle exception
        }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        enableExpandableList();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void enableExpandableList() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        expListView = (ExpandableListView) findViewById(R.id.left_drawer);

        prepareListData(listDataHeader, listDataChild);
        listAdapter = new ExpandListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                 /*Toast.makeText(getApplicationContext(),
                 "Group Clicked " + listDataHeader.get(groupPosition),
                 Toast.LENGTH_SHORT).show();*/
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                /*Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_LONG).show();*/
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                /*Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_LONG).show();*/

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                /*Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_LONG)
                        .show();*/
                if(listDataHeader.get(groupPosition).toString().equals(internal)){//berita internal
                    if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals(jurusan)){
                        changeFragmentListUploadKriteria(new ListUpload(),jurusan, String.valueOf(sJurusan));
                    }else{
                        changeFragmentListUploadKriteria(new ListUpload(),fakultas, String.valueOf(sFakultas));
                    }
                }else{//berita external
                    if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals(politik)){
                        changeFragmentListUploadKriteria(new ListUpload(), politik, "4");
                    }else if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals(OR)){
                        changeFragmentListUploadKriteria(new ListUpload(), OR, "1");
                    }else if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals(spkBl)){
                        changeFragmentListUploadKriteria(new ListUpload(), spkBl, "2");
                    }else if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals(food)){
                        changeFragmentListUploadKriteria(new ListUpload(), food, "9");
                    }else if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals(kesehatan)){
                        changeFragmentListUploadKriteria(new ListUpload(), kesehatan, "10");
                    }else if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals(lifestyle)){
                        changeFragmentListUploadKriteria(new ListUpload(), lifestyle, "5");
                    }else if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals(otomotif)){
                        changeFragmentListUploadKriteria(new ListUpload(), otomotif, "8");
                    }else if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals(traveling)){
                        changeFragmentListUploadKriteria(new ListUpload(), traveling, "12");
                    }else if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals(fotografi)){
                        changeFragmentListUploadKriteria(new ListUpload(), fotografi, "11");
                    }else if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals(teknologi)){
                        changeFragmentListUploadKriteria(new ListUpload(), teknologi, "7");
                    }else if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals(hukum)){
                        changeFragmentListUploadKriteria(new ListUpload(), teknologi, "3");
                    }else{//finance
                        changeFragmentListUploadKriteria(new ListUpload(), finance, "6");
                    }
                }
                return false;
            }
        });
    }

    private void prepareListData(List<String> listDataHeader, Map<String,
                List<String>> listDataChild) {

        // Adding child data
        listDataHeader.add(internal);
        listDataHeader.add(external);

        // Adding child data
        List<String> header = new ArrayList<String>();
        header.add(jurusan);
        header.add(fakultas);

        // Adding child data
        List<String> top = new ArrayList<String>();
        top.add(politik);
        top.add(hukum);
        top.add(OR);
        top.add(spkBl);
        top.add(food);
        top.add(kesehatan);
        top.add(lifestyle);
        top.add(otomotif);
        top.add(traveling);
        top.add(fotografi);
        top.add(teknologi);
        top.add(finance);

        listDataChild.put(listDataHeader.get(0), header); // Header, Child data
        listDataChild.put(listDataHeader.get(1), top);
    }

    private void changeFragmentListUploadKriteria(Fragment targetFragment, String tipeberita, String idBerita){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FrmMainMenu, targetFragment)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .setCustomAnimations(R.anim.blink, R.anim.fade_in)
                .commit();
        Bundle extras = new Bundle();
        extras.putString("tipeBerita", tipeberita);
        extras.putString("idBerita", idBerita);
        targetFragment.setArguments(extras);
        drawer.closeDrawer(START);
    }
}
