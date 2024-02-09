package pacoteswati4star.createpdf.util;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import swati4star.createpdf.R;
import swati4star.createpdf.adapter.NovidadesAdapter;
import swati4star.createpdf.model.OQueHaDeNovo;

public class NovidadesUtil {

    private static NovidadesUtil instancia;

    public static NovidadesUtil getInstancia() {
        if (instancia == null) {
            instancia = new NovidadesUtil();
        }
        return instancia;
    }

    public void exibirDialog(Context contexto) {
        final Dialog dialogo = new Dialog(contexto);
        dialogo.setContentView(R.layout.fragment_whats_new);
        RecyclerView recyclerView = dialogo.findViewById(R.id.whatsNewListView);
        TextView titulo = dialogo.findViewById(R.id.title);
        Button continuarButton = dialogo.findViewById(R.id.continueButton);
        continuarButton.setText(R.string.whatsnew_continue);
        titulo.setText(R.string.whatsnew_title);
        try {
            JSONObject obj = new JSONObject(carregarJSONDoAsset(contexto));
            List<OQueHaDeNovo> novidades = extrairItensDoJSON(obj);
            NovidadesAdapter novidadesAdapter = new NovidadesAdapter(contexto, novidades);
            LinearLayoutManager layoutManager = new LinearLayoutManager(contexto, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(novidadesAdapter);
            dialogo.show();
            continuarButton.setOnClickListener(view -> dialogo.dismiss());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String carregarJSONDoAsset(Context contexto) {
        String json = null;
        try {
            InputStream inputStream = contexto.getAssets().open("whatsnew.json");
            int tamanho = inputStream.available();
            byte[] buffer = new byte[tamanho];
            int bytesRead = inputStream.read(buffer);
            inputStream.close();
            if (bytesRead != -1) {
                json = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private List<OQueHaDeNovo> extrairItensDoJSON(JSONObject objeto) {
        List<OQueHaDeNovo> listaNovidades = new ArrayList<>();
        try {
            JSONArray dados = objeto.getJSONArray("dados");
            for (int i = 0; i < dados.length(); i++) {
                JSONObject jsonObject = dados.getJSONObject(i);
                String titulo = jsonObject.optString("titulo");
                String conteudo = jsonObject.optString("conteudo");
                String iconeLocalizacao = jsonObject.optString("icone");
                OQueHaDeNovo novidade = new OQueHaDeNovo(titulo, conteudo, iconeLocalizacao);
                listaNovidades.add(novidade);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaNovidades;
    }
}
