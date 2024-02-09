package swati4star.createpdf.fragment;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import java.util.ArrayList;

public class TranslatePdfFragmentTest {
    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);
    @Test
    public void translateText_CorrectTranslation() {
        TranslatePdfFragment fragment = new TranslatePdfFragment();
        String originalText = "Hello, world!";
        String translatedText = fragment.translateText(originalText, "en", "pt");
        assertNotNull(translatedText);
        assertNotEquals(originalText, translatedText);
    }

    @Test
    public void onPopulate_PopulatesFileList() {
        TranslatePdfFragment fragment = new TranslatePdfFragment();
        ArrayList<String> paths = new ArrayList<>();
        paths.add("path1");
        paths.add("path2");
        BottomSheetPopulate mockPopulate = mock(BottomSheetPopulate.class);
        fragment.onPopulate(paths);
        verify(mockPopulate).populateBottomSheetWithPDFs(fragment);
    }

    @Test
    public void onItemClick_SetsFileNameCorrectly() {
        TranslatePdfFragment fragment = new TranslatePdfFragment();
        String path = "sample_path";
        String expectedFileName = "sample_filename.pdf";
        fragment.onItemClick(path);
        assertEquals(expectedFileName, fragment.mFileName);
    }


    @Test
    public void completeFunctionalityTest_SelectPdfAndTranslate() {

        Espresso.onView(ViewMatchers.withId(R.id.select_pdf_file)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.translate_pdf)).perform(click());
        intended(hasComponent(TranslateTextActivity.class.getName()));
    }
}
