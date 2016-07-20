package br.com.livro.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

	private static final Pattern regexAll = Pattern.compile("/carros");
	private static final Pattern regexById = Pattern.compile("/carros/([0-9]*)");

	// Verifica se a URL está no padrão "/carros/id"
	public static Long matchId(String requestUrl) {
		// Verifica o ID
		Matcher matcher = regexById.matcher(requestUrl);
		if (matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(1);
			if (matcher != null && s.trim().length() > 0) {
				Long id = Long.parseLong(s);
				return id;
			}
		}
		return null;
	}

	// Verifica se a URL está no padrão "/carros/id"
	public boolean matchAll(String requestUrl) {
		Matcher matcher = regexAll.matcher(requestUrl);
		return matcher.find();
	}
}
