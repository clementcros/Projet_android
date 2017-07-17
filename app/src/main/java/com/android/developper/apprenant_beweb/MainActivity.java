package com.android.developper.apprenant_beweb;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.android.developper.apprenant_beweb.Adapter.ListApprenantsAdapter;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity activity = this;

final JSONArray listJson = null;


        RequestQueue mRequestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); //1MB cap
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        String url = "http://10.216.0.138/apiBlackBooks";
        JsonArrayRequest apprenants = new JsonArrayRequest( url,

                new Response.Listener<JSONArray>()  {
            @Override

            public void onResponse(JSONArray response) {

                Toast.makeText(MainActivity.this,"Machin"+response, Toast.LENGTH_LONG).show();
                ListApprenantsAdapter adapter = new ListApprenantsAdapter(MainActivity.this ,response);
                ListView listApprenants = (ListView)findViewById(R.id.la_listview_apprenants);
                listApprenants.setAdapter(adapter);



            }


        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Faux, nul", Toast.LENGTH_LONG).show();
                    }


                });


        mRequestQueue.add(apprenants);



//        public static ArrayList<Personne> getPersonnes() {
//
//            ArrayList<Personne> personnes = new ArrayList<Personne>();
//
//            try {
//                String myurl= "http://10.216.0.138/apiBlackBooks/";
//
//                URL url = new URL(myurl);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//                InputStream inputStream = connection.getInputStream();
//            /*
//             * InputStreamOperations est une classe complémentaire:
//             * Elle contient une méthode InputStreamToString.
//             */
//                String result = InputStreamOperations.InputStreamToString(inputStream);
//
//                // On récupère le JSON complet
//                JSONObject jsonObject = new JSONObject(result);
//                // On récupère le tableau d'objets qui nous concernent
//                JSONArray array = new JSONArray(jsonObject.getString("personnes"));
//                // Pour tous les objets on récupère les infos
//                for (int i = 0; i < array.length(); i++) {
//                    // On récupère un objet JSON du tableau
//                    JSONObject obj = new JSONObject(array.getString(i));
//                    // On fait le lien Personne - Objet JSON
//                    Personne personne = new Personne();
//                    personne.setNom(obj.getString("nom"));
//                    personne.setPrenom(obj.getString("prenom"));
//                    // On ajoute la personne à la liste
//                    personnes.add(personne);
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            // On retourne la liste des personnes
//            return personnes;
//        }
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="http://www.google.com";
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        mTextView.setText("Response is: "+ response.substring(0,500));
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
//            }
//        });
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);
//

        Resources tableau = getResources();

        String[] identity = tableau.getStringArray(R.array.identity);
        final ListView listApprenants = (ListView)findViewById(R.id.la_listview_apprenants);
        //ListApprenantsAdapter adapter = new ListApprenantsAdapter(this, identity);
        //listApprenants.setAdapter(adapter);


    }
}
