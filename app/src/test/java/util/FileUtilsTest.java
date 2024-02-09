package util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static swati4star.createpdf.util.FileUtils.getFileName;

<<<<<<< HEAD
import android.app.Activity;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
=======
import org.junit.Before;
import org.junit.Test;
>>>>>>> 0fc0a24b3526dba6d4140589e0fc9390c6865281
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.util.TimeZone;

import swati4star.createpdf.util.FileInfoUtils;
import swati4star.createpdf.util.FileUtils;

@RunWith(MockitoJUnitRunner.class)
public class FileUtilsTest {

    private static final String FILE_PATH = "/a/b/";
    private static final String FILE_NAME = "c.pdf";

    @Mock
    File file;

    @Before
    public void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void when_CallingGetFormattedDate_Expect_CorrectDateReturned() {
        when(file.lastModified()).thenReturn(0L);
        assertThat(FileInfoUtils.getFormattedDate(file), is("Thu, Jan 01 at 00:00"));
    }

    @Test
    public void when_CallingGetFormattedSize_Expect_CorrectDateReturned() {
        when(file.length()).thenReturn(5242880L);
        assertThat(FileInfoUtils.getFormattedSize(file), is("5.00 MB"));
    }

    @Test
    public void when_CallingGetFileName_Expect_CorrectValueReturned() {
        assertThat(getFileName(FILE_PATH + FILE_NAME), is(FILE_NAME));
        assertThat(getFileName(""), is(""));
    }

    @Test
    public void when_CallingStaticGetFileName_Expect_CorrectValueReturned() {
        assertThat(getFileName(FILE_PATH + FILE_NAME), is(FILE_NAME));
        assertThat(getFileName(""), is(""));
    }

    @Test
<<<<<<< HEAD
    public void when_CallingStaticGetFileName_NULL_Expect_NULL() {
        Assert.assertNull(FileUtils.getFileName((String) null));
    }

    @Test
=======
>>>>>>> 0fc0a24b3526dba6d4140589e0fc9390c6865281
    public void when_CallingGetFileNameWithoutExtension_Expect_CorrectValueReturned() {
        assertThat(FileUtils.getFileNameWithoutExtension(FILE_PATH + FILE_NAME), is("c"));
        assertThat(FileUtils.getFileNameWithoutExtension(""), is(""));
    }

    @Test
<<<<<<< HEAD
    public void when_CallingGetFileNameWithoutExtension_NULL_Expect_NULL() {
        Assert.assertNull(FileUtils.getFileNameWithoutExtension(null));
    }

    @Test
=======
>>>>>>> 0fc0a24b3526dba6d4140589e0fc9390c6865281
    public void when_CallingGetFileDirectoryPath_Expect_CorrectValueReturned() {
        assertThat(FileUtils.getFileDirectoryPath(FILE_PATH + FILE_NAME), is(FILE_PATH));
        assertThat(FileUtils.getFileDirectoryPath(""), is(""));
    }
<<<<<<< HEAD

    @Test
    public void when_stripExtension_Expect_CorrectValueReturned() {
        FileUtils utils = new FileUtils(new Activity());
        assertThat(utils.stripExtension(FILE_NAME), is("c"));
        assertThat(utils.stripExtension("c"), is("c"));
        Assert.assertNull(utils.stripExtension(null));
    }

=======
>>>>>>> 0fc0a24b3526dba6d4140589e0fc9390c6865281
}