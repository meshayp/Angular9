# NPM
`NPM` - Node Package Manager - npm is the package manager for JavaScript and the world’s largest software registry.

#### Options to install npm packages:
* `Global` - one download for the current user (after we install from npm - we can access this package from any cli in our computer)

<br/>
Syntax to install global from npm:
```
npm install -g <PACKAGE-NAME>
```


* Per project    

Syntax to install local from npm:
```
npm install -s <PACKAGE-NAME>
```
## Init your project with npm
* To connect a project to npm run the following command:
```
npm init
```
This utility will walk you through creating a package.json file, that contains:
    * `name` - name can only contain URL-friendly characters.
    * `version` , `description`, `git repository`, `keywords`, `author`, `license` - this is usefull if we upload our project to npm
    * entry point: (index.js)
    * test command
* The content of `package.json` file:
```json
{
  "name": "app",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "author": "",
  "license": "ISC"
}
```

## Example of using a `npm package` from `npm`
* installation Via npm:
```
npm install emoji-regex
```
If the command run successfully, you should notice a new folder `node_modules/emoji-regex` in your project.

* Create a new file named `app.js` and insert into it this content:

```javascript
const emojiRegex = require('emoji-regex');

const text = `
\u{231A}: ⌚ default emoji presentation character (Emoji_Presentation)
\u{2194}\u{FE0F}: ↔️ default text presentation character rendered as emoji
\u{1F469}: 👩 emoji modifier base (Emoji_Modifier_Base)
\u{1F469}\u{1F3FF}: 👩🏿 emoji modifier base followed by a modifier
`;
 
const regex = emojiRegex();
let match;
while (match = regex.exec(text)) {
  const emoji = match[0];
  console.log(`Matched sequence ${ emoji } — code points: ${ [...emoji].length }`);
}
```

* Run the project with this command;
```
node app.js
```
Console output:
```
Matched sequence ⌚ — code points: 1
Matched sequence ⌚ — code points: 1
Matched sequence ↔️ — code points: 2
Matched sequence ↔️ — code points: 2
Matched sequence 👩 — code points: 1
Matched sequence 👩 — code points: 1
Matched sequence 👩🏿 — code points: 2
Matched sequence 👩🏿 — code points: 2
```