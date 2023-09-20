package first_task_t1c.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import first_task_t1c.dto.StringInfoResponse;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StringInfoServiceImpl implements StringInfoService{
    private final StringInfoResponse stringInfoResponse = new StringInfoResponse();

    @Override
    public StringInfoResponse getCharCount(String text) {
        Map<Character, Integer> charMap = getCharMap(text);
        Map<Character, Integer> sortedMap = charMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap( Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        stringInfoResponse.setStringInfoResponse(sortedMap);
        return stringInfoResponse;
    }

    private  Map<Character, Integer> getCharMap(String text)
    {
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        String regExpForWords = "[\\s\\p{Punct}]+";
        String cleanedText = text.replaceAll(regExpForWords, "");
        char[] chars = cleanedText.toCharArray();
        for (char ch: chars) {
            charFrequencyMap.put(ch, charFrequencyMap.getOrDefault(ch, 0) + 1);
        }
        return charFrequencyMap;
    }

}
