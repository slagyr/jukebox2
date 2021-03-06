class Artwork

  constructor: ->
    @covers = $('.album-cover')
    @covers.bind 'ajax:success', @render
    @load()

  load: ->
    @covers.each ->
      $cover = $(this)
      album = $cover.attr('data-album')
      artist = $cover.attr('data-artist')
      $.get('http://ws.audioscrobbler.com/2.0/', {"method": "album.getInfo", "api_key": "809bf298f1f11c57fbb680b1befdf476", "album": album, "artist": artist}, (data) ->
        $cover.trigger 'ajax:success', [data]
      )

  render: (e, info) ->
    size = $(this).attr('data-thumbnail')
    src = $(info).find("image[size=#{size}]").text()
    wrap = $('<a />', {'href': '#'})
    img = $('<img>', {'class': 'thumbnail', 'src': src})
    wrap.append(img)
    $(this).append(wrap)

$ -> new Artwork
