<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <html>
      <head>
        <title>Lista de Videojuegos</title>
      </head>
      <body>
        <h2>Videojuegos</h2>
        <table border="1">
          <tr bgcolor="#9acd32">
            <th>Nombre</th>
            <th>Plataforma y Año</th>
          </tr>
          <xsl:for-each select="Videojuegos/Videojuego">
            <tr>
              <td><xsl:value-of select="Nombre"/></td>
              <td><xsl:value-of select="concat(Plataforma, ' - ', Año)"/></td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
