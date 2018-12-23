# token-api

Just visit http://random-token.xyz/LENGTH to receive your token!

### wget: ###
`wget https://random-token.xyz/LENGTH`
### PHP: ###
``` php
$token = file_get_contents("https://random-token.xyz/LENGTH");
```
### Java: ###
``` java
private static String fetchToken(int length) throws IOException {
        URL url = new URL("https://random-token.xyz/" + length);
        
        InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        
        StringBuilder tokenBuiler = new StringBuilder();
        String line;
        
        while ((line = bufferedReader.readLine()) != null) {
            tokenBuiler.append(line);
        }
        
        return tokenBuiler.toString();
}
```

### You can get Tokens between 1 and 30 characters in length. ###




### Coming features: ###
  - no token collision
  - choice between upper-/lower-case, numbers or mix
