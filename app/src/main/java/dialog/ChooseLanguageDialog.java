package dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import co.tysa.MainActivity;
import co.tysa.R;

import Config.ConstValue;
import adapter.LanguageAdapter;
import util.LanguagePrefs;



public class ChooseLanguageDialog extends AlertDialog {

    private Context context;

    public ChooseLanguageDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        // ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_choose_language, null);
        this.setView(dialogView);

        try {
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } catch (Exception ex) {

        }

        LanguagePrefs languagePrefs = new LanguagePrefs(context);

        RecyclerView recyclerView = dialogView.findViewById(R.id.rv_choose_language);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        LanguageAdapter languageAdapter = new LanguageAdapter(context, new LanguageAdapter.onItemClick() {
            @Override
            public void OnItemClick(int position, ConstValue.getLanguageList lang) {
                languagePrefs.saveLanguage(lang.getValue());
                languagePrefs.initRTL(lang.getValue());
                dismiss();
                ((MainActivity) context).recreate();
            }
        });
        recyclerView.setAdapter(languageAdapter);

    }

   /* @Override
    public void onClick(View view) {

        LanguagePrefs languagePrefs = new LanguagePrefs(context);

        switch (view.getId()) {
            case R.id.btnEnglish:
                languagePrefs.saveLanguage("en");
                languagePrefs.initRTL("en");
                dismiss();

                ((MainActivity) context).finish();
                context.startActivity(((MainActivity) context).getIntent());

                break;
            case R.id.btnArabic:
                languagePrefs.saveLanguage("ar");
                languagePrefs.initRTL("ar");
                dismiss();

                ((MainActivity) context).finish();
                context.startActivity(((MainActivity) context).getIntent());

                break;
        }
    }
*/
}