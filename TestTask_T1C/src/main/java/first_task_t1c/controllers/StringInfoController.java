package first_task_t1c.controllers;

import first_task_t1c.services.StringInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import first_task_t1c.dto.StringInfoResponse;
import first_task_t1c.services.StringInfoService;

@AllArgsConstructor
@RestController
public class StringInfoController {

    @Autowired
    private StringInfoService stringInfoService;

    @Operation(summary = "Counting characters in string")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Characters are counted"),
            @ApiResponse(responseCode = "400", description = "The number of characters in string more than 140")
            })
    @PostMapping("/getStringInfo")
    public ResponseEntity<StringInfoResponse> getStringInfo(@RequestBody String text)
    {
        if(text.length() > 140)
        {
            return ResponseEntity.badRequest().body(new StringInfoResponse());
        }
        return ResponseEntity.ok(stringInfoService.getCharCount(text));
    }
}
