package rs.pparadigme.matf.funnyclicker.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.upgrades_fragment.*
import rs.pparadigme.matf.funnyclicker.R
import rs.pparadigme.matf.funnyclicker.adapters.ItemAdapter
import rs.pparadigme.matf.funnyclicker.utils.AppUtils
import rs.pparadigme.matf.funnyclicker.utils.Item

class UpgradesFragment : Fragment(){

    val TAG:String = this.javaClass.toString()

    var adapter: ItemAdapter? = null
    var itemsList = ArrayList<Item>()
    var selected = -1

    override fun onAttach(context: Context?) {
        Log.d(TAG, "onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return inflater!!.inflate(R.layout.upgrades_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        selected = -1
        /* load items - images */
        itemsList.add(Item("Man", R.drawable.ic_man_svgrepo_com,
            "Increase number of people by 1. People make science and food automatically.", 0))
        itemsList.add(Item("Wheel", R.drawable.ic_wheel,
            "Lower cost of people and pots.",0))
        itemsList.add(Item("Pot", R.drawable.ic_vase_30597,
            "Increase food storage by 30.",0))
        itemsList.add(Item("Science", R.drawable.ic_mouse_click,
            "Increase science per click.",0))
        itemsList.add(Item("Food", R.drawable.ic_mouse_click,
            "Increase food per click.",0))
        itemsList.add(Item("House", R.drawable.ic_simple_house,
            "Increase people capacity by 5.",0))
        itemsList.add(Item("Barn", R.drawable.ic_barn,
            "Increase food storage by 1000.",0))
        itemsList.add(Item("School", R.drawable.ic_elementary_school,
            "Increase science earned.",0))

        adapter = ItemAdapter(activity!!.applicationContext, itemsList)
        gvImages.adapter = adapter


        gvImages.setOnItemClickListener { parent, view, position, id ->
            textUpgradeValue.setText(" " + AppUtils.upgradeCosts[id.toInt()])
            textUpgradeInfo.setText(itemsList[id.toInt()].info)
            selected = id.toInt()
            //Toast.makeText(activity!!.applicationContext, "Clicked on position " + position, Toast.LENGTH_SHORT).show()
        }

        buttonBuyUpgrade.setOnClickListener {

            if(selected != -1 && AppUtils.foodAm >= AppUtils.upgradeCosts[selected]) {
                Toast.makeText(
                    activity!!.applicationContext,
                    "Sel" + selected + " " + AppUtils.upgradeCosts[selected],
                    Toast.LENGTH_SHORT
                )
                var available = true
                if (selected == 0) {
                    if(AppUtils.peopleCap > AppUtils.peopleAm){
                        AppUtils.peopleAm++
                    }
                    else{
                        available = false
                        Toast.makeText(activity!!.applicationContext, "Build more houses for people", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                if (selected == 1) {
                    //wheel
                    if (AppUtils.scienceResearched[2] > 0) {
                        AppUtils.upgradeCosts[0] = ((AppUtils.upgradeCosts[0] + 1) / 1.2).toInt()
                        AppUtils.upgradeCosts[2] = ((AppUtils.upgradeCosts[2] + 1) / 1.2).toInt()
                    } else {
                        available = false
                        Toast.makeText(activity!!.applicationContext, "Wheel not researched yet", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                if (selected == 2) {
                    //pot
                    if (AppUtils.scienceResearched[1] > 0) {
                        AppUtils.foodCap += 30
                    } else {
                        available = false
                        Toast.makeText(activity!!.applicationContext, "Pottery not researched yet", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                if (selected == 3) AppUtils.scienceCl++
                if (selected == 4) AppUtils.foodCl++

                if (selected == 5) {
                    if (AppUtils.scienceResearched[4] > 0) {
                        AppUtils.peopleCap += 5
                    } else {
                        available = false
                        Toast.makeText(activity!!.applicationContext, "Housing not researched yet", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                if (selected == 6) AppUtils.foodCap += 1000

                if (selected == 7) AppUtils.scientistsEff++

                if (available) {
                    AppUtils.foodAm -= AppUtils.upgradeCosts[selected]
                    AppUtils.upgradeCosts[selected] = (AppUtils.upgradeCosts[selected] * AppUtils.costCoef).toInt()

                    textUpgradeValue.setText(" " + AppUtils.upgradeCosts[selected])

                    Toast.makeText(activity!!.applicationContext, "Bought upgrade", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            else {
                Toast.makeText(activity!!.applicationContext, "Not enough food to buy this", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach")
        super.onDetach()
    }

}