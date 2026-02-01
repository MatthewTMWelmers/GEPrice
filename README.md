# GEPrice

This is the service layer for the GEPrice application.  This code allows for the
querying of ge prices, as well as the uploading and maintenance of GE submissions.

## Endpoints

### Health
The health endpoint is a heartbeat endpoint, allowing verification that the server
is up

Endpoint
```
GET <api_endpoint>/api/health
```

Example Response 
```
{
    "status": "ok",
    "timestamp": "2026-02-01T20:46:17.895664100Z"
}
```

### Bosses

#### All Bosses

This endpoint returns information about all bosses

Endpoint

```
GET <api_endpoint>/api/bosses/all
```

Example Response
```json
[
    {
        "id": 1,
        "name": "Arch-Glacor",
        "wikiUrl": "https://runescape.wiki/w/Arch-Glacor",
        "icon": "Arch-Glacor.png",
        "createdAt": "2026-02-01T20:46:17.895664100Z"
    },
    {
        "id": 2,
        "name": "Amascut, the Devourer",
        "wikiUrl": "https://runescape.wiki/w/Amascut,_the_Devourer",
        "icon": "Amascut.png",
        "createdAt": "2026-02-01T20:46:17.895664100Z"
    }
]
```

#### Boss Information

This endpoint returns the information about a specific boss

Endpoint

```
GET <api_endpoint>/api/bosses/1
```

Example Response
```json
{
    "id": 1,
    "name": "Arch-Glacor",
    "wikiUrl": "https://runescape.wiki/w/Arch-Glacor",
    "icon": "Arch-Glacor.png",
    "createdAt": "2026-02-01T20:46:17.895664100Z"
}
```

Error Response
```json
{
    "error": "Boss not found"
}
```

### Submissions

### Items