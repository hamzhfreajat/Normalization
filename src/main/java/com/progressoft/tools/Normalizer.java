package com.progressoft.tools;

import java.nio.file.Path;

public interface Normalizer {

    ScoringSummary zscore(Path csvPath, Path destPath, String colToStandardize);
}
