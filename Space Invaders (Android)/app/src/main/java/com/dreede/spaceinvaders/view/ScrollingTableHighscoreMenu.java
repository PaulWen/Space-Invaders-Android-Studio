package com.dreede.spaceinvaders.view;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.dreede.spaceinvaders.R;
 

/**
 * Die Klasse {@link ScrollingTableHighscoreMenu} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class ScrollingTableHighscoreMenu extends LinearLayout {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private List<Integer> colWidths;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link ScrollingTableHighscoreMenu}.
	 * 
	 *  @param context
	 */
	public ScrollingTableHighscoreMenu(Context context) {
		super(context);

		// Datenfelder initialisieren
		colWidths = new LinkedList<Integer>();
	}
	
	/**
	 * Der Konstruktor der Klasse {@link ScrollingTableHighscoreMenu}.
	 * 
	 *  @param context
	 *  @param attrs
	 */
	public ScrollingTableHighscoreMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		// Datenfelder initialisieren
		colWidths = new LinkedList<Integer>();
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		colWidths.clear();

		TableLayout header = (TableLayout) findViewById(R.id.tableHeaderHighscoreMenu);
		TableLayout body = (TableLayout) findViewById(R.id.tableBodyHighscoreMenu);

		
		// die größte Zellenbreite jeder Spalte bestimmen
		for (int rownum = 0; rownum < header.getChildCount(); rownum++) {
			TableRow row = (TableRow) header.getChildAt(rownum);
			for (int cellnum = 0; cellnum < row.getChildCount(); cellnum++) {
				View cell = row.getChildAt(cellnum);
				Integer cellWidth = cell.getWidth();
				if (colWidths.size() <= cellnum) {
					colWidths.add(cellWidth);
				} else {
					Integer current = colWidths.get(cellnum);
					if (cellWidth > current) {
						colWidths.remove(cellnum);
						colWidths.add(cellnum, cellWidth);
					}
				}
			}
		}
		for (int rownum = 0; rownum < body.getChildCount(); rownum++) {
			TableRow row = (TableRow) body.getChildAt(rownum);
			for (int cellnum = 0; cellnum < row.getChildCount(); cellnum++) {
				View cell = row.getChildAt(cellnum);
				Integer cellWidth = cell.getWidth();
				if (colWidths.size() <= cellnum) {
					colWidths.add(cellWidth);
				} else {
					Integer current = colWidths.get(cellnum);
					if (cellWidth > current) {
						colWidths.remove(cellnum);
						colWidths.add(cellnum, cellWidth);
					}
				}
			}
		}

		
		// die Größe jeder Zelle anpassen
		for (int rownum = 0; rownum < header.getChildCount(); rownum++) {
			TableRow row = (TableRow) header.getChildAt(rownum);
			for (int cellnum = 0; cellnum < row.getChildCount(); cellnum++) {
				View cell = row.getChildAt(cellnum);
				TableRow.LayoutParams params = (TableRow.LayoutParams) cell
						.getLayoutParams();
				params.width = colWidths.get(cellnum);
			}
		}
		for (int rownum = 0; rownum < body.getChildCount(); rownum++) {
			TableRow row = (TableRow) body.getChildAt(rownum);
			for (int cellnum = 0; cellnum < row.getChildCount(); cellnum++) {
				View cell = row.getChildAt(cellnum);
				TableRow.LayoutParams params = (TableRow.LayoutParams) cell
						.getLayoutParams();
				params.width = colWidths.get(cellnum);
			}
		}
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}


/**
 * 
 * 
 * Beispiel einer Layout-Datei, welche von dieser Klasse dann richtig formatiert wird:
 * 
 * 
<?xml version="1.0" encoding="utf-8"?>
<com.dreede.spaceinvaders.view.ScrollingTable xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="match_parent"
    android:layout_weight="1"
    android:orientation="vertical" >

    <TableLayout
        android:id="@+id/HeaderTable"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >

        <TableRow style="@style/HeaderRow" >

            <TextView
                style="@style/HeaderText"
                android:text="Col 1" />

            <TextView
                style="@style/HeaderText"
                android:text="Col 2" />

            <TextView
                style="@style/HeaderText"
                android:text="Col 3" />

            <TextView
                style="@style/HeaderText"
                android:text="Col 4" />
        </TableRow>
    </TableLayout>

    <ScrollView
        android:layout_width="fill_parent"
        android:layout_height="wrap_content" >

        <TableLayout
            android:id="@+id/BodyTable"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content" >

            <TableRow style="@style/BodyRow" >

                <TextView
                    style="@style/BodyText"
                    android:text="Cell 1,1" />

                <TextView
                    style="@style/BodyText"
                    android:text="Cell 1,2" />

                <TextView
                    style="@style/BodyText"
                    android:text="Cell 1,3" />

                <TextView
                    style="@style/BodyText"
                    android:text="Cell 1,4" />
            </TableRow>

            <TableRow style="@style/BodyRow" >

                <TextView
                    style="@style/BodyText"
                    android:text="Cell 8,1" />

                <TextView
                    style="@style/BodyText"
                    android:text="Cell 8,2" />

                <TextView
                    style="@style/BodyText"
                    android:text="Cell 8,3" />

                <TextView
                    style="@style/BodyText"
                    android:text="Cell 8,4" />
            </TableRow>
        </TableLayout>
    </ScrollView>

</com.dreede.spaceinvaders.view.ScrollingTable>
*/


