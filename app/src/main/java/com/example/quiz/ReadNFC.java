package com.example.quiz;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.quiz.model.TagContent;
import com.example.quiz.utils.Animation;

public class ReadNFC  extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    public static String TAG = "TAG";
    private NfcReaderViewModel nfcReaderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readnfc);

        nfcReaderViewModel = new ViewModelProvider(this).get(NfcReaderViewModel.class);

        //Get default NfcAdapter and PendingIntent instances
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        // check NFC feature:
        if (nfcAdapter == null) {
            Toast.makeText(this, "Votre téléphone n'est pas compatible NFC", Toast.LENGTH_SHORT).show();
            finish();
        }
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE);


    }

    @Override
    protected void onResume() {
        super.onResume();

        //Enable NFC foreground detection
        if (nfcAdapter != null) {
            if (!nfcAdapter.isEnabled()) {
                Toast.makeText(this, "Votre NFC n'est pas activé.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                finish();
            } else {
                nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
            }
        } else {
            Toast.makeText(this, "Votre téléphone n'est pas compatible NFC.", Toast.LENGTH_LONG).show();
            finish();        }

        nfcReaderViewModel.getReadFailed().observe(this, readFailed -> {
            Toast.makeText(this, readFailed.getMessage(), Toast.LENGTH_SHORT).show();
        });

        nfcReaderViewModel.getTagRead().observe(this, readSuccess -> {
            for (TagContent s : readSuccess) {
                //TODO Réaliser les actions en fonction du contenu du tag
                // TODO Si c'est un numéro de téléphone, lancer un appel
                // TODO Si c'est une page web lancer un navigateur pour afficher la page
                // TODO Sinon afficher le contenu dans la textview
                Toast.makeText(this, s.getContent(), Toast.LENGTH_SHORT).show();
            }
        });

        animateNfcTag();

    }

    private void animateNfcTag() {
        Animation.animateCard(findViewById(R.id.card_image));
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Disable NFC foreground detection
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }

    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getAction();
        // check the event was triggered by the tag discovery
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {

            // get the tag object from the received intent
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            // TODO Vérifier que le tag est bien formaté en NDEF
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            nfcReaderViewModel.processNfcTag(rawMsgs);
            // TODO Si non, afficher un message d'erreur et rediriger l'utilisateur vers l'activité d'écriture
        }
    }
}
