import React from "react"
import PropTypes from "prop-types"
import _ from "lodash"
import bitcoinImg from "../images/bitcoin.svg"
import ethernumImg from "../images/ethernum.svg"
import litecoinImg from "../images/litecoin.svg"
import zcashImg from "../images/zcash.svg"

const CurrencyType = {
  bitcoin: 1,
  litecoin: 2,
  ethernum: 3,
  zcash: 4,
  default: 5,
}

const getCurrencyTypeFromName = (name) => {
  if (_.isEqual(name, "bitcoin")) {
    return CurrencyType.bitcoin
  } else if (_.isEqual(name, "ethernum")) {
    return CurrencyType.ethernum
  } else if (_.isEqual(name, "litecoin")) {
    return CurrencyType.litecoin
  } else if (_.isEqual(name, "zcash")) {
    return CurrencyType.zcash
  } else {
    return CurrencyType.default
  }
}

const getCurrencyImage = (name) => {
  switch (getCurrencyTypeFromName(name)) {
    case CurrencyType.bitcoin :
      return bitcoinImg
    case CurrencyType.litecoin :
      return litecoinImg
    case CurrencyType.ethernum :
      return ethernumImg
    case CurrencyType.zcash :
      return zcashImg
    case CurrencyType.default :
      return bitcoinImg
  }
}

const Image = (props) => {
  const {name} = props
  return (
      <img className="currency-icon" src={getCurrencyImage(name)}/>
  )
}

Image.propTypes = {
  name: PropTypes.string.isRequired
}

export default Image