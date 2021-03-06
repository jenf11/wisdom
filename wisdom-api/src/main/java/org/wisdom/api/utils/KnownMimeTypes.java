/*
 * #%L
 * Wisdom-Framework
 * %%
 * Copyright (C) 2013 - 2014 Wisdom Framework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.wisdom.api.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A list of known mime types.
 */
public class KnownMimeTypes {
    
    public static final Map<String, String> EXTENSIONS;
    
    private KnownMimeTypes(){
        //Hide implicit constructor
    }

    static {
        EXTENSIONS = new TreeMap<>();
        EXTENSIONS.put("3dm", "x-world/x-3dmf");
        EXTENSIONS.put("3dmf", "x-world/x-3dmf");
        EXTENSIONS.put("7z", "application/x-7z-compressed");
        EXTENSIONS.put("a", "application/octet-stream");
        EXTENSIONS.put("aab", "application/x-authorware-bin");
        EXTENSIONS.put("aam", "application/x-authorware-map");
        EXTENSIONS.put("aas", "application/x-authorware-seg");
        EXTENSIONS.put("abc", "text/vndabc");
        EXTENSIONS.put("ace", "application/x-ace-compressed");
        EXTENSIONS.put("acgi", "text/html");
        EXTENSIONS.put("afl", "video/animaflex");
        EXTENSIONS.put("ai", "application/postscript");
        EXTENSIONS.put("aif", "audio/aiff");
        EXTENSIONS.put("aifc", "audio/aiff");
        EXTENSIONS.put("aiff", "audio/aiff");
        EXTENSIONS.put("aim", "application/x-aim");
        EXTENSIONS.put("aip", "text/x-audiosoft-intra");
        EXTENSIONS.put("alz", "application/x-alz-compressed");
        EXTENSIONS.put("ani", "application/x-navi-animation");
        EXTENSIONS.put("aos", "application/x-nokia-9000-communicator-add-on-software");
        EXTENSIONS.put("aps", "application/mime");
        EXTENSIONS.put("arc", "application/x-arc-compressed");
        EXTENSIONS.put("arj", "application/arj");
        EXTENSIONS.put("art", "image/x-jg");
        EXTENSIONS.put("asf", "video/x-ms-asf");
        EXTENSIONS.put("asm", "text/x-asm");
        EXTENSIONS.put("asp", "text/asp");
        EXTENSIONS.put("asx", "application/x-mplayer2");
        EXTENSIONS.put("au", "audio/basic");
        EXTENSIONS.put("avi", "video/x-msvideo");
        EXTENSIONS.put("avs", "video/avs-video");
        EXTENSIONS.put("bcpio", "application/x-bcpio");
        EXTENSIONS.put("bin", "application/mac-binary");
        EXTENSIONS.put("bmp", "image/bmp");
        EXTENSIONS.put("boo", "application/book");
        EXTENSIONS.put("book", "application/book");
        EXTENSIONS.put("boz", "application/x-bzip2");
        EXTENSIONS.put("bsh", "application/x-bsh");
        EXTENSIONS.put("bz2", "application/x-bzip2");
        EXTENSIONS.put("bz", "application/x-bzip");
        EXTENSIONS.put("c++", "text/plain");
        EXTENSIONS.put("c", "text/x-c");
        EXTENSIONS.put("cab", "application/vnd.ms-cab-compressed");
        EXTENSIONS.put("cat", "application/vndms-pkiseccat");
        EXTENSIONS.put("cc", "text/x-c");
        EXTENSIONS.put("ccad", "application/clariscad");
        EXTENSIONS.put("cco", "application/x-cocoa");
        EXTENSIONS.put("cdf", "application/cdf");
        EXTENSIONS.put("cer", "application/pkix-cert");
        EXTENSIONS.put("cha", "application/x-chat");
        EXTENSIONS.put("chat", "application/x-chat");
        EXTENSIONS.put("chrt", "application/vnd.kde.kchart");
        EXTENSIONS.put("class", "application/java");
        EXTENSIONS.put("# ? class", "application/java-vm");
        EXTENSIONS.put("com", "text/plain");
        EXTENSIONS.put("conf", "text/plain");
        EXTENSIONS.put("cpio", "application/x-cpio");
        EXTENSIONS.put("cpp", "text/x-c");
        EXTENSIONS.put("cpt", "application/mac-compactpro");
        EXTENSIONS.put("crl", "application/pkcs-crl");
        EXTENSIONS.put("crt", "application/pkix-cert");
        EXTENSIONS.put("crx", "application/x-chrome-extension");
        EXTENSIONS.put("csh", "text/x-scriptcsh");
        EXTENSIONS.put("css", "text/css");
        EXTENSIONS.put("csv", "text/csv");
        EXTENSIONS.put("cxx", "text/plain");
        EXTENSIONS.put("dar", "application/x-dar");
        EXTENSIONS.put("dcr", "application/x-director");
        EXTENSIONS.put("deb", "application/x-debian-package");
        EXTENSIONS.put("deepv", "application/x-deepv");
        EXTENSIONS.put("def", "text/plain");
        EXTENSIONS.put("der", "application/x-x509-ca-cert");
        EXTENSIONS.put("dif", "video/x-dv");
        EXTENSIONS.put("dir", "application/x-director");
        EXTENSIONS.put("divx", "video/divx");
        EXTENSIONS.put("dl", "video/dl");
        EXTENSIONS.put("dmg", "application/x-apple-diskimage");
        EXTENSIONS.put("doc", "application/msword");
        EXTENSIONS.put("dot", "application/msword");
        EXTENSIONS.put("dp", "application/commonground");
        EXTENSIONS.put("drw", "application/drafting");
        EXTENSIONS.put("dump", "application/octet-stream");
        EXTENSIONS.put("dv", "video/x-dv");
        EXTENSIONS.put("dvi", "application/x-dvi");
        EXTENSIONS.put("dwf", "drawing/x-dwf=(old)");
        EXTENSIONS.put("dwg", "application/acad");
        EXTENSIONS.put("dxf", "application/dxf");
        EXTENSIONS.put("dxr", "application/x-director");
        EXTENSIONS.put("el", "text/x-scriptelisp");
        EXTENSIONS.put("elc", "application/x-bytecodeelisp=(compiled=elisp)");
        EXTENSIONS.put("eml", "message/rfc822");
        EXTENSIONS.put("env", "application/x-envoy");
        EXTENSIONS.put("eps", "application/postscript");
        EXTENSIONS.put("es", "application/x-esrehber");
        EXTENSIONS.put("etx", "text/x-setext");
        EXTENSIONS.put("evy", "application/envoy");
        EXTENSIONS.put("exe", "application/octet-stream");
        EXTENSIONS.put("f77", "text/x-fortran");
        EXTENSIONS.put("f90", "text/x-fortran");
        EXTENSIONS.put("f", "text/x-fortran");
        EXTENSIONS.put("fdf", "application/vndfdf");
        EXTENSIONS.put("fif", "application/fractals");
        EXTENSIONS.put("fli", "video/fli");
        EXTENSIONS.put("flo", "image/florian");
        EXTENSIONS.put("flv", "video/x-flv");
        EXTENSIONS.put("flx", "text/vndfmiflexstor");
        EXTENSIONS.put("fmf", "video/x-atomic3d-feature");
        EXTENSIONS.put("for", "text/x-fortran");
        EXTENSIONS.put("fpx", "image/vndfpx");
        EXTENSIONS.put("frl", "application/freeloader");
        EXTENSIONS.put("funk", "audio/make");
        EXTENSIONS.put("g3", "image/g3fax");
        EXTENSIONS.put("g", "text/plain");
        EXTENSIONS.put("gif", "image/gif");
        EXTENSIONS.put("gl", "video/gl");
        EXTENSIONS.put("gsd", "audio/x-gsm");
        EXTENSIONS.put("gsm", "audio/x-gsm");
        EXTENSIONS.put("gsp", "application/x-gsp");
        EXTENSIONS.put("gss", "application/x-gss");
        EXTENSIONS.put("gtar", "application/x-gtar");
        EXTENSIONS.put("gz", "application/x-compressed");
        EXTENSIONS.put("gzip", "application/x-gzip");
        EXTENSIONS.put("h", "text/x-h");
        EXTENSIONS.put("hdf", "application/x-hdf");
        EXTENSIONS.put("help", "application/x-helpfile");
        EXTENSIONS.put("hgl", "application/vndhp-hpgl");
        EXTENSIONS.put("hh", "text/x-h");
        EXTENSIONS.put("hlb", "text/x-script");
        EXTENSIONS.put("hlp", "application/hlp");
        EXTENSIONS.put("hpg", "application/vndhp-hpgl");
        EXTENSIONS.put("hpgl", "application/vndhp-hpgl");
        EXTENSIONS.put("hqx", "application/binhex");
        EXTENSIONS.put("hta", "application/hta");
        EXTENSIONS.put("htc", "text/x-component");
        EXTENSIONS.put("htm", "text/html");
        EXTENSIONS.put("html", "text/html");
        EXTENSIONS.put("htmls", "text/html");
        EXTENSIONS.put("htt", "text/webviewhtml");
        EXTENSIONS.put("htx", "text/html");
        EXTENSIONS.put("ice", "x-conference/x-cooltalk");
        EXTENSIONS.put("ico", "image/x-icon");
        EXTENSIONS.put("ics", "text/calendar");
        EXTENSIONS.put("icz", "text/calendar");
        EXTENSIONS.put("idc", "text/plain");
        EXTENSIONS.put("ief", "image/ief");
        EXTENSIONS.put("iefs", "image/ief");
        EXTENSIONS.put("iges", "application/iges");
        EXTENSIONS.put("igs", "application/iges");
        EXTENSIONS.put("ima", "application/x-ima");
        EXTENSIONS.put("imap", "application/x-httpd-imap");
        EXTENSIONS.put("inf", "application/inf");
        EXTENSIONS.put("ins", "application/x-internett-signup");
        EXTENSIONS.put("ip", "application/x-ip2");
        EXTENSIONS.put("isu", "video/x-isvideo");
        EXTENSIONS.put("it", "audio/it");
        EXTENSIONS.put("iv", "application/x-inventor");
        EXTENSIONS.put("ivr", "i-world/i-vrml");
        EXTENSIONS.put("ivy", "application/x-livescreen");
        EXTENSIONS.put("jam", "audio/x-jam");
        EXTENSIONS.put("jav", "text/x-java-source");
        EXTENSIONS.put("java", "text/x-java-source");
        EXTENSIONS.put("jcm", "application/x-java-commerce");
        EXTENSIONS.put("jfif-tbnl", "image/jpeg");
        EXTENSIONS.put("jfif", "image/jpeg");
        EXTENSIONS.put("jnlp", "application/x-java-jnlp-file");
        EXTENSIONS.put("jpe", "image/jpeg");
        EXTENSIONS.put("jpeg", "image/jpeg");
        EXTENSIONS.put("jpg", "image/jpeg");
        EXTENSIONS.put("jps", "image/x-jps");
        EXTENSIONS.put("js", "application/javascript");
        EXTENSIONS.put("json", "application/json");
        EXTENSIONS.put("jut", "image/jutvision");
        EXTENSIONS.put("kar", "audio/midi");
        EXTENSIONS.put("karbon", "application/vnd.kde.karbon");
        EXTENSIONS.put("kfo", "application/vnd.kde.kformula");
        EXTENSIONS.put("flw", "application/vnd.kde.kivio");
        EXTENSIONS.put("kml", "application/vnd.google-earth.kml+xml");
        EXTENSIONS.put("kmz", "application/vnd.google-earth.kmz");
        EXTENSIONS.put("kon", "application/vnd.kde.kontour");
        EXTENSIONS.put("kpr", "application/vnd.kde.kpresenter");
        EXTENSIONS.put("kpt", "application/vnd.kde.kpresenter");
        EXTENSIONS.put("ksp", "application/vnd.kde.kspread");
        EXTENSIONS.put("kwd", "application/vnd.kde.kword");
        EXTENSIONS.put("kwt", "application/vnd.kde.kword");
        EXTENSIONS.put("ksh", "text/x-scriptksh");
        EXTENSIONS.put("la", "audio/nspaudio");
        EXTENSIONS.put("lam", "audio/x-liveaudio");
        EXTENSIONS.put("latex", "application/x-latex");
        EXTENSIONS.put("lha", "application/lha");
        EXTENSIONS.put("lhx", "application/octet-stream");
        EXTENSIONS.put("list", "text/plain");
        EXTENSIONS.put("lma", "audio/nspaudio");
        EXTENSIONS.put("log", "text/plain");
        EXTENSIONS.put("lsp", "text/x-scriptlisp");
        EXTENSIONS.put("lst", "text/plain");
        EXTENSIONS.put("lsx", "text/x-la-asf");
        EXTENSIONS.put("ltx", "application/x-latex");
        EXTENSIONS.put("lzh", "application/octet-stream");
        EXTENSIONS.put("lzx", "application/lzx");
        EXTENSIONS.put("m1v", "video/mpeg");
        EXTENSIONS.put("m2a", "audio/mpeg");
        EXTENSIONS.put("m2v", "video/mpeg");
        EXTENSIONS.put("m3u", "audio/x-mpegurl");
        EXTENSIONS.put("m", "text/x-m");
        EXTENSIONS.put("man", "application/x-troff-man");
        EXTENSIONS.put("manifest", "text/cache-manifest");
        EXTENSIONS.put("map", "application/x-navimap");
        EXTENSIONS.put("mar", "text/plain");
        EXTENSIONS.put("mbd", "application/mbedlet");
        EXTENSIONS.put("mc$", "application/x-magic-cap-package-10");
        EXTENSIONS.put("mcd", "application/mcad");
        EXTENSIONS.put("mcf", "text/mcf");
        EXTENSIONS.put("mcp", "application/netmc");
        EXTENSIONS.put("me", "application/x-troff-me");
        EXTENSIONS.put("mht", "message/rfc822");
        EXTENSIONS.put("mhtml", "message/rfc822");
        EXTENSIONS.put("mid", "application/x-midi");
        EXTENSIONS.put("midi", "application/x-midi");
        EXTENSIONS.put("mif", "application/x-frame");
        EXTENSIONS.put("mime", "message/rfc822");
        EXTENSIONS.put("mjf", "audio/x-vndaudioexplosionmjuicemediafile");
        EXTENSIONS.put("mjpg", "video/x-motion-jpeg");
        EXTENSIONS.put("mm", "application/base64");
        EXTENSIONS.put("mme", "application/base64");
        EXTENSIONS.put("mod", "audio/mod");
        EXTENSIONS.put("moov", "video/quicktime");
        EXTENSIONS.put("mov", "video/quicktime");
        EXTENSIONS.put("movie", "video/x-sgi-movie");
        EXTENSIONS.put("mp2", "audio/mpeg");
        EXTENSIONS.put("mp3", "audio/mpeg3");
        EXTENSIONS.put("mp4", "video/mp4");
        EXTENSIONS.put("mpa", "audio/mpeg");
        EXTENSIONS.put("mpc", "application/x-project");
        EXTENSIONS.put("mpe", "video/mpeg");
        EXTENSIONS.put("mpeg", "video/mpeg");
        EXTENSIONS.put("mpg", "video/mpeg");
        EXTENSIONS.put("mpga", "audio/mpeg");
        EXTENSIONS.put("mpp", "application/vndms-project");
        EXTENSIONS.put("mpt", "application/x-project");
        EXTENSIONS.put("mpv", "application/x-project");
        EXTENSIONS.put("mpx", "application/x-project");
        EXTENSIONS.put("mrc", "application/marc");
        EXTENSIONS.put("ms", "application/x-troff-ms");
        EXTENSIONS.put("mv", "video/x-sgi-movie");
        EXTENSIONS.put("my", "audio/make");
        EXTENSIONS.put("mzz", "application/x-vndaudioexplosionmzz");
        EXTENSIONS.put("nap", "image/naplps");
        EXTENSIONS.put("naplps", "image/naplps");
        EXTENSIONS.put("nc", "application/x-netcdf");
        EXTENSIONS.put("ncm", "application/vndnokiaconfiguration-message");
        EXTENSIONS.put("nif", "image/x-niff");
        EXTENSIONS.put("niff", "image/x-niff");
        EXTENSIONS.put("nix", "application/x-mix-transfer");
        EXTENSIONS.put("nsc", "application/x-conference");
        EXTENSIONS.put("nvd", "application/x-navidoc");
        EXTENSIONS.put("o", "application/octet-stream");
        EXTENSIONS.put("oda", "application/oda");
        EXTENSIONS.put("odb", "application/vnd.oasis.opendocument.database");
        EXTENSIONS.put("odc", "application/vnd.oasis.opendocument.chart");
        EXTENSIONS.put("odf", "application/vnd.oasis.opendocument.formula");
        EXTENSIONS.put("odg", "application/vnd.oasis.opendocument.graphics");
        EXTENSIONS.put("odi", "application/vnd.oasis.opendocument.image");
        EXTENSIONS.put("odm", "application/vnd.oasis.opendocument.text-master");
        EXTENSIONS.put("odp", "application/vnd.oasis.opendocument.presentation");
        EXTENSIONS.put("ods", "application/vnd.oasis.opendocument.spreadsheet");
        EXTENSIONS.put("odt", "application/vnd.oasis.opendocument.text");
        EXTENSIONS.put("oga", "audio/ogg");
        EXTENSIONS.put("ogg", "audio/ogg");
        EXTENSIONS.put("ogv", "video/ogg");
        EXTENSIONS.put("omc", "application/x-omc");
        EXTENSIONS.put("omcd", "application/x-omcdatamaker");
        EXTENSIONS.put("omcr", "application/x-omcregerator");
        EXTENSIONS.put("otc", "application/vnd.oasis.opendocument.chart-template");
        EXTENSIONS.put("otf", "application/vnd.oasis.opendocument.formula-template");
        EXTENSIONS.put("otg", "application/vnd.oasis.opendocument.graphics-template");
        EXTENSIONS.put("oth", "application/vnd.oasis.opendocument.text-web");
        EXTENSIONS.put("oti", "application/vnd.oasis.opendocument.image-template");
        EXTENSIONS.put("otm", "application/vnd.oasis.opendocument.text-master");
        EXTENSIONS.put("otp", "application/vnd.oasis.opendocument.presentation-template");
        EXTENSIONS.put("ots", "application/vnd.oasis.opendocument.spreadsheet-template");
        EXTENSIONS.put("ott", "application/vnd.oasis.opendocument.text-template");
        EXTENSIONS.put("p10", "application/pkcs10");
        EXTENSIONS.put("p12", "application/pkcs-12");
        EXTENSIONS.put("p7a", "application/x-pkcs7-signature");
        EXTENSIONS.put("p7c", "application/pkcs7-mime");
        EXTENSIONS.put("p7m", "application/pkcs7-mime");
        EXTENSIONS.put("p7r", "application/x-pkcs7-certreqresp");
        EXTENSIONS.put("p7s", "application/pkcs7-signature");
        EXTENSIONS.put("p", "text/x-pascal");
        EXTENSIONS.put("part", "application/pro_eng");
        EXTENSIONS.put("pas", "text/pascal");
        EXTENSIONS.put("pbm", "image/x-portable-bitmap");
        EXTENSIONS.put("pcl", "application/vndhp-pcl");
        EXTENSIONS.put("pct", "image/x-pict");
        EXTENSIONS.put("pcx", "image/x-pcx");
        EXTENSIONS.put("pdb", "chemical/x-pdb");
        EXTENSIONS.put("pdf", "application/pdf");
        EXTENSIONS.put("pfunk", "audio/make");
        EXTENSIONS.put("pgm", "image/x-portable-graymap");
        EXTENSIONS.put("pic", "image/pict");
        EXTENSIONS.put("pict", "image/pict");
        EXTENSIONS.put("pkg", "application/x-newton-compatible-pkg");
        EXTENSIONS.put("pko", "application/vndms-pkipko");
        EXTENSIONS.put("pl", "text/x-scriptperl");
        EXTENSIONS.put("plx", "application/x-pixclscript");
        EXTENSIONS.put("pm4", "application/x-pagemaker");
        EXTENSIONS.put("pm5", "application/x-pagemaker");
        EXTENSIONS.put("pm", "text/x-scriptperl-module");
        EXTENSIONS.put("png", "image/png");
        EXTENSIONS.put("pnm", "application/x-portable-anymap");
        EXTENSIONS.put("pot", "application/mspowerpoint");
        EXTENSIONS.put("pov", "model/x-pov");
        EXTENSIONS.put("ppa", "application/vndms-powerpoint");
        EXTENSIONS.put("ppm", "image/x-portable-pixmap");
        EXTENSIONS.put("pps", "application/mspowerpoint");
        EXTENSIONS.put("ppt", "application/mspowerpoint");
        EXTENSIONS.put("ppz", "application/mspowerpoint");
        EXTENSIONS.put("pre", "application/x-freelance");
        EXTENSIONS.put("prt", "application/pro_eng");
        EXTENSIONS.put("ps", "application/postscript");
        EXTENSIONS.put("psd", "application/octet-stream");
        EXTENSIONS.put("pvu", "paleovu/x-pv");
        EXTENSIONS.put("pwz", "application/vndms-powerpoint");
        EXTENSIONS.put("py", "text/x-scriptphyton");
        EXTENSIONS.put("pyc", "applicaiton/x-bytecodepython");
        EXTENSIONS.put("qcp", "audio/vndqcelp");
        EXTENSIONS.put("qd3", "x-world/x-3dmf");
        EXTENSIONS.put("qd3d", "x-world/x-3dmf");
        EXTENSIONS.put("qif", "image/x-quicktime");
        EXTENSIONS.put("qt", "video/quicktime");
        EXTENSIONS.put("qtc", "video/x-qtc");
        EXTENSIONS.put("qti", "image/x-quicktime");
        EXTENSIONS.put("qtif", "image/x-quicktime");
        EXTENSIONS.put("ra", "audio/x-pn-realaudio");
        EXTENSIONS.put("ram", "audio/x-pn-realaudio");
        EXTENSIONS.put("rar", "application/x-rar-compressed");
        EXTENSIONS.put("ras", "application/x-cmu-raster");
        EXTENSIONS.put("rast", "image/cmu-raster");
        EXTENSIONS.put("rexx", "text/x-scriptrexx");
        EXTENSIONS.put("rf", "image/vndrn-realflash");
        EXTENSIONS.put("rgb", "image/x-rgb");
        EXTENSIONS.put("rm", "application/vndrn-realmedia");
        EXTENSIONS.put("rmi", "audio/mid");
        EXTENSIONS.put("rmm", "audio/x-pn-realaudio");
        EXTENSIONS.put("rmp", "audio/x-pn-realaudio");
        EXTENSIONS.put("rng", "application/ringing-tones");
        EXTENSIONS.put("rnx", "application/vndrn-realplayer");
        EXTENSIONS.put("roff", "application/x-troff");
        EXTENSIONS.put("rp", "image/vndrn-realpix");
        EXTENSIONS.put("rpm", "audio/x-pn-realaudio-plugin");
        EXTENSIONS.put("rt", "text/vndrn-realtext");
        EXTENSIONS.put("rtf", "text/richtext");
        EXTENSIONS.put("rtx", "text/richtext");
        EXTENSIONS.put("rv", "video/vndrn-realvideo");
        EXTENSIONS.put("s", "text/x-asm");
        EXTENSIONS.put("s3m", "audio/s3m");
        EXTENSIONS.put("s7z", "application/x-7z-compressed");
        EXTENSIONS.put("saveme", "application/octet-stream");
        EXTENSIONS.put("sbk", "application/x-tbook");
        EXTENSIONS.put("scm", "text/x-scriptscheme");
        EXTENSIONS.put("sdml", "text/plain");
        EXTENSIONS.put("sdp", "application/sdp");
        EXTENSIONS.put("sdr", "application/sounder");
        EXTENSIONS.put("sea", "application/sea");
        EXTENSIONS.put("set", "application/set");
        EXTENSIONS.put("sgm", "text/x-sgml");
        EXTENSIONS.put("sgml", "text/x-sgml");
        EXTENSIONS.put("sh", "text/x-scriptsh");
        EXTENSIONS.put("shar", "application/x-bsh");
        EXTENSIONS.put("shtml", "text/x-server-parsed-html");
        EXTENSIONS.put("sid", "audio/x-psid");
        EXTENSIONS.put("skd", "application/x-koan");
        EXTENSIONS.put("skm", "application/x-koan");
        EXTENSIONS.put("skp", "application/x-koan");
        EXTENSIONS.put("skt", "application/x-koan");
        EXTENSIONS.put("sit", "application/x-stuffit");
        EXTENSIONS.put("sitx", "application/x-stuffitx");
        EXTENSIONS.put("sl", "application/x-seelogo");
        EXTENSIONS.put("smi", "application/smil");
        EXTENSIONS.put("smil", "application/smil");
        EXTENSIONS.put("snd", "audio/basic");
        EXTENSIONS.put("sol", "application/solids");
        EXTENSIONS.put("spc", "text/x-speech");
        EXTENSIONS.put("spl", "application/futuresplash");
        EXTENSIONS.put("spr", "application/x-sprite");
        EXTENSIONS.put("sprite", "application/x-sprite");
        EXTENSIONS.put("spx", "audio/ogg");
        EXTENSIONS.put("src", "application/x-wais-source");
        EXTENSIONS.put("ssi", "text/x-server-parsed-html");
        EXTENSIONS.put("ssm", "application/streamingmedia");
        EXTENSIONS.put("sst", "application/vndms-pkicertstore");
        EXTENSIONS.put("step", "application/step");
        EXTENSIONS.put("stl", "application/sla");
        EXTENSIONS.put("stp", "application/step");
        EXTENSIONS.put("sv4cpio", "application/x-sv4cpio");
        EXTENSIONS.put("sv4crc", "application/x-sv4crc");
        EXTENSIONS.put("svf", "image/vnddwg");
        EXTENSIONS.put("svg", "image/svg+xml");
        EXTENSIONS.put("svr", "application/x-world");
        EXTENSIONS.put("swf", "application/x-shockwave-flash");
        EXTENSIONS.put("t", "application/x-troff");
        EXTENSIONS.put("talk", "text/x-speech");
        EXTENSIONS.put("tar", "application/x-tar");
        EXTENSIONS.put("tbk", "application/toolbook");
        EXTENSIONS.put("tcl", "text/x-scripttcl");
        EXTENSIONS.put("tcsh", "text/x-scripttcsh");
        EXTENSIONS.put("tex", "application/x-tex");
        EXTENSIONS.put("texi", "application/x-texinfo");
        EXTENSIONS.put("texinfo", "application/x-texinfo");
        EXTENSIONS.put("text", "text/plain");
        EXTENSIONS.put("tgz", "application/gnutar");
        EXTENSIONS.put("tif", "image/tiff");
        EXTENSIONS.put("tiff", "image/tiff");
        EXTENSIONS.put("tr", "application/x-troff");
        EXTENSIONS.put("tsi", "audio/tsp-audio");
        EXTENSIONS.put("tsp", "application/dsptype");
        EXTENSIONS.put("tsv", "text/tab-separated-values");
        EXTENSIONS.put("turbot", "image/florian");
        EXTENSIONS.put("txt", "text/plain");
        EXTENSIONS.put("uil", "text/x-uil");
        EXTENSIONS.put("uni", "text/uri-list");
        EXTENSIONS.put("unis", "text/uri-list");
        EXTENSIONS.put("unv", "application/i-deas");
        EXTENSIONS.put("uri", "text/uri-list");
        EXTENSIONS.put("uris", "text/uri-list");
        EXTENSIONS.put("ustar", "application/x-ustar");
        EXTENSIONS.put("uu", "text/x-uuencode");
        EXTENSIONS.put("uue", "text/x-uuencode");
        EXTENSIONS.put("vcd", "application/x-cdlink");
        EXTENSIONS.put("vcf", "text/x-vcard");
        EXTENSIONS.put("vcard", "text/x-vcard");
        EXTENSIONS.put("vcs", "text/x-vcalendar");
        EXTENSIONS.put("vda", "application/vda");
        EXTENSIONS.put("vdo", "video/vdo");
        EXTENSIONS.put("vew", "application/groupwise");
        EXTENSIONS.put("viv", "video/vivo");
        EXTENSIONS.put("vivo", "video/vivo");
        EXTENSIONS.put("vmd", "application/vocaltec-media-desc");
        EXTENSIONS.put("vmf", "application/vocaltec-media-file");
        EXTENSIONS.put("voc", "audio/voc");
        EXTENSIONS.put("vos", "video/vosaic");
        EXTENSIONS.put("vox", "audio/voxware");
        EXTENSIONS.put("vqe", "audio/x-twinvq-plugin");
        EXTENSIONS.put("vqf", "audio/x-twinvq");
        EXTENSIONS.put("vql", "audio/x-twinvq-plugin");
        EXTENSIONS.put("vrml", "application/x-vrml");
        EXTENSIONS.put("vrt", "x-world/x-vrt");
        EXTENSIONS.put("vsd", "application/x-visio");
        EXTENSIONS.put("vst", "application/x-visio");
        EXTENSIONS.put("vsw", "application/x-visio");
        EXTENSIONS.put("w60", "application/wordperfect60");
        EXTENSIONS.put("w61", "application/wordperfect61");
        EXTENSIONS.put("w6w", "application/msword");
        EXTENSIONS.put("wav", "audio/wav");
        EXTENSIONS.put("wb1", "application/x-qpro");
        EXTENSIONS.put("wbmp", "image/vnd.wap.wbmp");
        EXTENSIONS.put("web", "application/vndxara");
        EXTENSIONS.put("wiz", "application/msword");
        EXTENSIONS.put("wk1", "application/x-123");
        EXTENSIONS.put("wmf", "windows/metafile");
        EXTENSIONS.put("wml", "text/vnd.wap.wml");
        EXTENSIONS.put("wmlc", "application/vnd.wap.wmlc");
        EXTENSIONS.put("wmls", "text/vnd.wap.wmlscript");
        EXTENSIONS.put("wmlsc", "application/vnd.wap.wmlscriptc");
        EXTENSIONS.put("word", "application/msword");
        EXTENSIONS.put("wp5", "application/wordperfect");
        EXTENSIONS.put("wp6", "application/wordperfect");
        EXTENSIONS.put("wp", "application/wordperfect");
        EXTENSIONS.put("wpd", "application/wordperfect");
        EXTENSIONS.put("wq1", "application/x-lotus");
        EXTENSIONS.put("wri", "application/mswrite");
        EXTENSIONS.put("wrl", "application/x-world");
        EXTENSIONS.put("wrz", "model/vrml");
        EXTENSIONS.put("wsc", "text/scriplet");
        EXTENSIONS.put("wsrc", "application/x-wais-source");
        EXTENSIONS.put("wtk", "application/x-wintalk");
        EXTENSIONS.put("x-png", "image/png");
        EXTENSIONS.put("xbm", "image/x-xbitmap");
        EXTENSIONS.put("xdr", "video/x-amt-demorun");
        EXTENSIONS.put("xgz", "xgl/drawing");
        EXTENSIONS.put("xif", "image/vndxiff");
        EXTENSIONS.put("xl", "application/excel");
        EXTENSIONS.put("xla", "application/excel");
        EXTENSIONS.put("xlb", "application/excel");
        EXTENSIONS.put("xlc", "application/excel");
        EXTENSIONS.put("xld", "application/excel");
        EXTENSIONS.put("xlk", "application/excel");
        EXTENSIONS.put("xll", "application/excel");
        EXTENSIONS.put("xlm", "application/excel");
        EXTENSIONS.put("xls", "application/excel");
        EXTENSIONS.put("xlt", "application/excel");
        EXTENSIONS.put("xlv", "application/excel");
        EXTENSIONS.put("xlw", "application/excel");
        EXTENSIONS.put("xm", "audio/xm");
        EXTENSIONS.put("xml", "text/xml");
        EXTENSIONS.put("xmz", "xgl/movie");
        EXTENSIONS.put("xpix", "application/x-vndls-xpix");
        EXTENSIONS.put("xpm", "image/x-xpixmap");
        EXTENSIONS.put("xsr", "video/x-amt-showrun");
        EXTENSIONS.put("xwd", "image/x-xwd");
        EXTENSIONS.put("xyz", "chemical/x-pdb");
        EXTENSIONS.put("z", "application/x-compress");
        EXTENSIONS.put("zip", "application/zip");
        EXTENSIONS.put("zoo", "application/octet-stream");
        EXTENSIONS.put("zsh", "text/x-scriptzsh");

        //Office 2007 mess - http://wdg.uncc.edu/Microsoft_Office_2007_MIME_extensions_for_Apache_and_IIS"
        EXTENSIONS.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        EXTENSIONS.put("docm", "application/vnd.ms-word.document.macroEnabled.12");
        EXTENSIONS.put("dotx", "application/vnd.openxmlformats-officedocument.wordprocessingml.template");
        EXTENSIONS.put("dotm", "application/vnd.ms-word.template.macroEnabled.12");
        EXTENSIONS.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        EXTENSIONS.put("xlsm", "application/vnd.ms-excel.sheet.macroEnabled.12");
        EXTENSIONS.put("xltx", "application/vnd.openxmlformats-officedocument.spreadsheetml.template");
        EXTENSIONS.put("xltm", "application/vnd.ms-excel.template.macroEnabled.12");
        EXTENSIONS.put("xlsb", "application/vnd.ms-excel.sheet.binary.macroEnabled.12");
        EXTENSIONS.put("xlam", "application/vnd.ms-excel.addin.macroEnabled.12");
        EXTENSIONS.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        EXTENSIONS.put("pptm", "application/vnd.ms-powerpoint.presentation.macroEnabled.12");
        EXTENSIONS.put("ppsx", "application/vnd.openxmlformats-officedocument.presentationml.slideshow");
        EXTENSIONS.put("ppsm", "application/vnd.ms-powerpoint.slideshow.macroEnabled.12");
        EXTENSIONS.put("potx", "application/vnd.openxmlformats-officedocument.presentationml.template");
        EXTENSIONS.put("potm", "application/vnd.ms-powerpoint.template.macroEnabled.12");
        EXTENSIONS.put("ppam", "application/vnd.ms-powerpoint.addin.macroEnabled.12");
        EXTENSIONS.put("sldx", "application/vnd.openxmlformats-officedocument.presentationml.slide");
        EXTENSIONS.put("sldm", "application/vnd.ms-powerpoint.slide.macroEnabled.12");
        EXTENSIONS.put("thmx", "application/vnd.ms-officetheme ");
        EXTENSIONS.put("onetoc", "application/onenote");
        EXTENSIONS.put("onetoc2", "application/onenote");
        EXTENSIONS.put("onetmp", "application/onenote");
        EXTENSIONS.put("onepkg", "application/onenote");

        //iWork"
        EXTENSIONS.put("key", "application/x-iwork-keynote-sffkey");
        EXTENSIONS.put("kth", "application/x-iwork-keynote-sffkth");
        EXTENSIONS.put("nmbtemplate", "application/x-iwork-numbers-sfftemplate");
        EXTENSIONS.put("numbers", "application/x-iwork-numbers-sffnumbers");
        EXTENSIONS.put("pages", "application/x-iwork-pages-sffpages");
        EXTENSIONS.put("template", "application/x-iwork-pages-sfftemplate");

        //Extensions for Mozilla apps (Firefox and friends)
        EXTENSIONS.put("xpi", "application/x-xpinstall");
    }
    
    public static List<String> COMPRESSED_MIME;
    
    static{
    	//From http://en.wikipedia.org/wiki/List_of_archive_formats
    	COMPRESSED_MIME = new ArrayList<String>();
    	addMimeToCompressedWithExtension("bz2");
    	addMimeToCompressedWithExtension("gz");
    	addMimeToCompressedWithExtension("gzip");
    	addMimeToCompressedWithExtension("lzma");
    	addMimeToCompressedWithExtension("z");
    	addMimeToCompressedWithExtension("7z");
    	addMimeToCompressedWithExtension("s7z");
    	addMimeToCompressedWithExtension("ace");
    	addMimeToCompressedWithExtension("alz");
    	addMimeToCompressedWithExtension("arc");
    	addMimeToCompressedWithExtension("arj");
    	addMimeToCompressedWithExtension("cab");
    	addMimeToCompressedWithExtension("cpt");
    	addMimeToCompressedWithExtension("dar");
    	addMimeToCompressedWithExtension("dmg");
    	addMimeToCompressedWithExtension("ice");
    	addMimeToCompressedWithExtension("lha");
    	addMimeToCompressedWithExtension("lzx");
    	addMimeToCompressedWithExtension("rar");
    	addMimeToCompressedWithExtension("sit");
    	addMimeToCompressedWithExtension("sitx");
    	addMimeToCompressedWithExtension("tar");
    	addMimeToCompressedWithExtension("tgz");
    	addMimeToCompressedWithExtension("zip");
    	addMimeToCompressedWithExtension("zoo");
    	
    	//TODO complete list or group below (binary formats)
    	addMimeGroups("video/", "image/", "audio/");
    }
    
    private static void addMimeToCompressedWithExtension(String extension){
    	String mime = EXTENSIONS.get(extension);
    	if(mime != null && !COMPRESSED_MIME.contains(mime)){
    		COMPRESSED_MIME.add(mime);
    	}
    }

    private static void addMimeGroups(String... groups){
    	for(String mimeType : EXTENSIONS.values()){
	    	for(String group : groups){
	    		if(mimeType.startsWith(group) && !COMPRESSED_MIME.contains(mimeType)){
	    			COMPRESSED_MIME.add(mimeType);
	    		}
	    	}
    	}
    }
    
    public static String getMimeTypeByExtension(String extension) {
        return EXTENSIONS.get(extension);
    }
}
