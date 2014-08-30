// Menuing code.
//
// You don't have to tell me the positioning mechanism stinks. I'm well
// aware of it, having spent hours fighting with IE about positioning,
// and I just don't have the time nor inclination to write something that
// will reposition all these popup menus after the rest of the page is
// laid out.

menuList = new Array(
					"", 1, -302, 42,
					"Galleries", ">artists",
					"Contests", ">contests",
					"Resources", ">resources",
					"fractalus.com", ">extra",
					"",

					"artists", 2, 110, 0,
					"Damien Jones", "/gallery/",
					"Sharon Webb", "/sharon/",
					"Alice Kelley", "/cheshirecat/",
					"Linda Allison", "/gumbycat/",
					"Kerry Mitchell", "/kerry/",
					"Jack &amp; Margaret Valero", "/jack/",
					"Sylvie Gallet", "/sylvie/",
					"Mark Townsend", "/fdimentia/",
					"Paul DeCelle", "/paul/",
					"Daniel Kuzmenka", "/dan/",
					"",

					"contests", 2, 110, 22,
					"FractInt Contest '97", "http://www.fractalartcontests.com/1997/",
					"Fractal Art '98 Contest", "http://www.fractalartcontests.com/1998/",
					"Fractal-Art '99 Contest", "http://www.fractalartcontests.com/1999/",
					"Fractal-Art 2000 Contest", "http://www.fractalartcontests.com/2000/",
					"",

					"resources", 2, 110, 44,
					"Infinite Fractal Loop", "http://www.infinitefractalloop.com/",
					"Fractal Information", "/info/",
					"Fractal-Art FAQ", "/fractal-art-faq/",
					"File Libraries", "/downloads/",
					"Mailing Lists", "/lists/",
					"Other Sites", "/others/",
					"",

					"extra", 2, 110, 66,
					"About", "/about/",
					"Send Feedback", "/feedback/",
					"Site Map", "/map/",
					"Search", "/search/",
					"",

					"."
					);

menuTimer = null;
menuShowing = new Array();
menuCount = 0;

function doLayout()
{
	if (!document.all) return;			// Requires document.all; too tired for Netscape.

	document.write('<DIV STYLE="visibility: hidden; position: absolute; top: 0px; left: 0px;"><FORM METHOD="GET" ACTION="" NAME="menuForm"></FORM></DIV>');

	var i = 0;
	var nest = 0;
	var parentx = document.body.offsetWidth / 2;
	var parenty = 0;
	var topx = 0;
	var topy = 0;

	while (menuList[i] != '.')			// Not the end-of-list item.
	{
		// Begin menu block.
		nest = menuList[i+1];			// Extract nesting level.
		if (menuList[i] == '')			// Root menu item, positioned relative to parent object.
		{
			topx = parentx + menuList[i+2];
			topy = parenty + menuList[i+3];
			document.write('<DIV STYLE="position: absolute; top: ' + topy + 'px; left: ' + topx + 'px; cursor: hand; visibility: hidden" ID="menubox">');
		}
		else							// This is a submenu, positioned relative to a previous menu object.
			document.write('<DIV STYLE="position: absolute; top: ' + (topy+menuList[i+3]) + 'px; left: ' + (topx+menuList[i+2]) + 'px; cursor: hand; visibility: hidden" ID="menubox-' + menuList[i] + '">');
		document.write('<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0><TR><TD COLSPAN=3 BGCOLOR="#CCCCCC"><IMG SRC="../common/blank.gif" WIDTH=1 HEIGHT=1 ALT=""></TD></TR>');
		document.write('<TR><TD BGCOLOR="#CCCCCC"><IMG SRC="../common/blank.gif" WIDTH=1 HEIGHT=1 ALT=""></TD><TD BGCOLOR="#FFFFFF" STYLE="filter:alpha(opacity=90)">');
		document.write('<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 STYLE="position: relative">');
		i += 4;

		while (menuList[i] != '')		// Not the end-of-sublist item.
		{
			if (menuList[i+1].substr(0,1) == '>')	// Submenu item.
				document.write('<TR onMouseOver="doMenuOver(this,' + i + ',' + nest + ')" onMouseOut="doMenuOut(this,' + i + ',' + nest + ')" ID="menuitem-' + menuList[i+1].substr(1) +
					'"><TD><NOBR>&nbsp;' + menuList[i] + '&nbsp;</NOBR></TD><TD ALIGN=RIGHT>&nbsp;<IMG SRC="../common/bullet-r.gif" WIDTH=8 HEIGHT=8 ALT="" ALIGN=MIDDLE>&nbsp;</TD></TR>');
			else						// Selection item.
				document.write('<TR onMouseOver="doMenuOver(this,' + i + ',' + nest + ')" onMouseOut="doMenuOut(this,' + i + ',' + nest + ')" onClick="return doMenuClick(this,' + i + ')"><TD COLSPAN=2><NOBR>&nbsp;' +
					menuList[i] + '&nbsp;</NOBR></TD></TR>');
			i += 2;
		}
		i++;

		document.write('</TABLE></TD><TD BGCOLOR="#999999"><IMG SRC="../common/blank.gif" WIDTH=1 HEIGHT=1 ALT=""></TD></TR>');
		document.write('<TR><TD COLSPAN=3 BGCOLOR="#999999"><IMG SRC="../common/blank.gif" WIDTH=1 HEIGHT=1 ALT=""></TD></TR></TABLE></DIV>');
	}
}

function doMenuButtonOver()
{
	inside('menu');
	return true;
}

function doMenuButtonOut()
{
	outside('menu');
	return true;
}

function clearMenu()
{
	while (menuCount)
	{
		menuCount--;
		hideMenuName(menuShowing[menuCount],menuCount);
	}
	hideMenuName(menuShowing[menuCount],menuCount);

	document.all['link-menu'].onmouseout = doMenuButtonOut;
	outside('menu');
}

function doMenuOver(obj,idx,nest)
{
	if (menuTimer != null)
		window.clearTimeout(menuTimer);
	obj.style.backgroundColor = '#B0E8C8';
	if (menuList[idx+1].substr(0,1) == '>')		// This has a sub-menu attached.
		showMenuName('menubox-' + menuList[idx+1].substr(1),nest);
}

function doMenuOut(obj,idx,nest)
{
	obj.style.backgroundColor = 'transparent';
	if (menuTimer != null)
		window.clearTimeout(menuTimer);
	menuTimer = window.setTimeout(clearMenu, 250);
}

function doMenuClick(obj,idx)
{
	document.menuForm.action = menuList[idx+1];
	document.menuForm.submit();
	return false;
}

function showMenu(idx,nest)
{
	if (idx == 0)
		itemname = 'menubox';
	else
		itemname = 'menubox-' + menuList[idx];
	showMenuName(itemname,nest);

	document.all['link-menu'].onmouseout = null;
}

function showMenuName(name,nest)
{
	while (nest < menuCount)
	{
		menuCount--;
		hideMenuName(menuShowing[menuCount],menuCount);
	}
	menuShowing[menuCount++] = name;
	menuobj = document.all[name];
	menuobj.style.visibility = "visible";
}

function hideMenu(idx,nest)
{
	if (idx == 0)
		itemname = 'menubox';
	else
		itemname = 'menubox-' + menuList[idx];
	hideMenuName(itemname,nest);
}

function hideMenuName(name,nest)
{
	menuobj = document.all[name];
	menuobj.style.visibility = "hidden";
}
