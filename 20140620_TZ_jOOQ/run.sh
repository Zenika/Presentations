#!/bin/bash
echo "Start server: "
echo "   URL: http://localhost:8000/"
echo "   Stop: Ctrl-C"
echo ""
python -m SimpleHTTPServer

# Pour Python 3.x, remplacer SimpleHTTPServer par http.server
# Pour changer le port, ajouter le port désiré après SimpleHTTPServer : python -m SimpleHTTPServer 8080
