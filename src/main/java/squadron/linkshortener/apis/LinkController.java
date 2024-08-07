package squadron.linkshortener.apis;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import squadron.linkshortener.models.Link;
import squadron.linkshortener.services.LinkService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/links")
@RequiredArgsConstructor
public class LinkController {
    private final LinkService linkService;

    @GetMapping("/{shortUrl}")
    public void redirect(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        Link link = linkService.getOriginalLink(shortUrl);
        response.sendRedirect(link.getOriginalUrl());
    }


    @PostMapping
    public ResponseEntity<Link> createShortLink(String originalLink, String username) throws URISyntaxException, MalformedURLException {
        Link shortLink = linkService.createShortUrl(originalLink, username);
        return ResponseEntity.ok(shortLink);
    }
}
